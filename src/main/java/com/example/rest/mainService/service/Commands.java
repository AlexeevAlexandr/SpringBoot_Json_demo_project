package com.example.rest.mainService.service;

import com.example.rest.model.JsonList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Commands implements JsonListAddToDatabase, JsonMethods{

    private Transaction transaction = null;

    @Override
    public URL getUrlConnect(String inputUrl) throws IOException {
        URL url = new URL(inputUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int response = conn.getResponseCode();
        if (response != 200) {
            throw new RuntimeException("HttpResponseCode: " + response);
        }
        return url;
    }

    @Override
    public StringBuilder getDataThroughUrl(URL url) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (Scanner sc = new Scanner(url.openStream())){
            while (sc.hasNext()) {
                stringBuilder.append(sc.nextLine());
            }
        }catch (Exception e){
            throw new IOException();
        }
        return stringBuilder;
    }

    @Override
    public JSONArray setDataToJsonArray(StringBuilder inline, String string) throws ParseException {
        JSONParser parse = new JSONParser();
        JSONObject jObj = (JSONObject) parse.parse(inline.toString());
        return (JSONArray) jObj.get(string);
    }

    @Override
    public void setToDatabase(JSONArray jArr) throws IOException {
        for (Object jArrObj : jArr) {
            JSONObject jsonObject = (JSONObject) jArrObj;

            String hash = jsonObject.get("hash").toString();
            String value = jsonObject.toString();

           addDataToDatabase(hash, value);
        }
    }

    @Override
    public void addDataToDatabase(String hash, String value) throws IOException {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession())
        {
            transaction = session.beginTransaction();
            session.save(new JsonList(hash, value));
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new IOException();
        }
    }
}