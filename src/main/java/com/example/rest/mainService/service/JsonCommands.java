package com.example.rest.mainService.service;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;

public interface JsonCommands {

    URL getUrlConnect(String inputUrl) throws IOException;

    StringBuilder getDataThroughUrl(URL url) throws IOException;

    JSONArray setDataToJsonArray(StringBuilder inline, String dataHeader) throws ParseException;

    void setToDatabase(JSONArray jArr) throws IOException;
}