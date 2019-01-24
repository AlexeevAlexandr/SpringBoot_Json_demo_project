package com.example.rest;

import com.example.rest.service.DoWork;
import com.example.rest.service.ReadFromFileLinks;
import java.util.List;

public class Main{
    public static void main(String[] args) {
        ReadFromFileLinks links = new ReadFromFileLinks();

        String dataHeader = "data";
        List<String> listUrls = links.getLinks();

        for (String url : listUrls) {
            new DoWork().doWork(url, dataHeader);
        }
    }
}