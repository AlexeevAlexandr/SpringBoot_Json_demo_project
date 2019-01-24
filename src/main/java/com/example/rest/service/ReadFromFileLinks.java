package com.example.rest.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFromFileLinks {
    public List<String> getLinks() {
        List<String> linksList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader("src/main/resources/listOfLinks.csv"))) {
            while (scanner.hasNext()) {
                linksList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return linksList;
    }
}