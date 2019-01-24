package com.example.rest.mainService.service;

import java.io.IOException;

interface JsonListAddToDatabase {

    void addDataToDatabase(String hash, String value) throws IOException;
}
