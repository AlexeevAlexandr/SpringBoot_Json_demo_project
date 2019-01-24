package com.example.rest.service;

import com.example.rest.model.JsonList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Commands implements JsonListAddToDatabase, JsonMethods{

    private Transaction transaction = null;

    @Override
    public void add(String hash, String value) {
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
            e.printStackTrace();
        }
    }

    @Override
    public void setToDatabase(JSONArray jArr) {
        try {
            for (Object jArrObj : jArr) {
                JSONObject jsonObject = (JSONObject) jArrObj;

                String keyID = jsonObject.get("hash").toString();
                String value = jsonObject.toString();

                new Commands().add(keyID, value);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public JSONArray getJsonArray(StringBuilder inline, String string){
        try {
            JSONParser parse = new JSONParser();
            JSONObject jObj = (JSONObject) parse.parse(inline.toString());
            return (JSONArray) jObj.get(string);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public URL getUrlConnect(String inputUrl) {
        URL url = null;
        try {
            url = new URL(inputUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int response = conn.getResponseCode();
            if (response != 200) {
                throw new RuntimeException("HttpResponseCode: " + response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }

    @Override
    public StringBuilder getDataThroughUrl(URL url){
        StringBuilder stringBuilder = new StringBuilder();
        try (Scanner sc = new Scanner(url.openStream())){
            while (sc.hasNext()) {
                stringBuilder.append(sc.nextLine());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return stringBuilder;
    }
}
