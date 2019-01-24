package com.example.rest.service;

import org.json.simple.JSONArray;

import java.net.URL;

public interface JsonMethods {

    void setToDatabase(JSONArray jArr);

    JSONArray getJsonArray(StringBuilder inline, String string);

    URL getUrlConnect(String inputUrl);

    StringBuilder getDataThroughUrl(URL url);
}
