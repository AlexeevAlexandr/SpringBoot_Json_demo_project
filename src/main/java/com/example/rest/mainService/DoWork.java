package com.example.rest.mainService;

import com.example.rest.mainService.service.Commands;
import com.example.rest.mainService.service.JsonCommands;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;

public class DoWork {

    public void doWork(String inputUrl, String dataHeader) throws IOException, ParseException {
        JsonCommands jsonCommands = new Commands();

        URL url = jsonCommands.getUrlConnect(inputUrl);
        StringBuilder dataFromUrl = jsonCommands.getDataThroughUrl(url);
        JSONArray jArr = jsonCommands.setDataToJsonArray(dataFromUrl, dataHeader);
        jsonCommands.setToDatabase(jArr);
    }
}