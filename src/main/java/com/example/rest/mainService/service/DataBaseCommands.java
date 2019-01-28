package com.example.rest.mainService.service;

import java.io.IOException;
import java.util.List;

public interface DataBaseCommands {

    void addDataToDatabase(String hash, String value) throws IOException;

    List getDataFromDatabase() throws IOException;

    List getDataFromDatabaseByHash(String hash) throws IOException;
}
