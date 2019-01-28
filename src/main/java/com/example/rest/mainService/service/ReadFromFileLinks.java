package com.example.rest.mainService.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFromFileLinks {

    public List<String> getLinks() throws IOException {
        List<String> linksList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader("src/main/resources/listOfLinks.csv"))) {
            while (scanner.hasNext()) {
                linksList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new IOException();
        }
        return linksList;
    }
}