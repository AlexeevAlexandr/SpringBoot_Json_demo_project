package com.example.rest.service;

import org.json.simple.JSONArray;

import java.net.URL;

public class DoWork {
    public void doWork(String inputUrl, String dataHeader) {
        Commands command = new Commands();

        URL url = command.getUrlConnect(inputUrl);
        StringBuilder dataFromUrl = command.getDataThroughUrl(url);
        JSONArray jArr = command.getJsonArray(dataFromUrl, dataHeader);
        command.setToDatabase(jArr);
    }
}