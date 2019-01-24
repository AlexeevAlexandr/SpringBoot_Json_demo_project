package com.example.rest.mainService.service;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;

public class DoWork {

    public void doWork(String inputUrl, String dataHeader) throws IOException, ParseException {
        JsonMethods jsonMethods = new Commands();

        URL url = jsonMethods.getUrlConnect(inputUrl);
        StringBuilder dataFromUrl = jsonMethods.getDataThroughUrl(url);
        JSONArray jArr = jsonMethods.setDataToJsonArray(dataFromUrl, dataHeader);
        jsonMethods.setToDatabase(jArr);
    }
}