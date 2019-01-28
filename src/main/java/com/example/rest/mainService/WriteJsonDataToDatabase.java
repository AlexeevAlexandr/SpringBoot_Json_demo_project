package com.example.rest.mainService;

import com.example.rest.mainService.service.ReadFromFileLinks;

import java.util.List;

/**
*This class to start writing JSON data from links
*Links are contained in the 'listOfLinks.csv' file.
*The file is contained in the resource package.
*The program reads the list of links, receives data from them one by one and writes to the database.
**/

public class WriteJsonDataToDatabase{
    public static void main(String[] args) {
        ReadFromFileLinks links = new ReadFromFileLinks();
        DoWork doWork = new DoWork();

        String dataHeader = "data";

        try {
            List<String> listUrls = links.getLinks();
            for (String url : listUrls) {
            doWork.doWork(url, dataHeader);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}