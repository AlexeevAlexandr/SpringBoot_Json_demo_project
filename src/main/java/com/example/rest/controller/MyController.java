package com.example.rest.controller;

import com.example.rest.mainService.DoWork;
import com.example.rest.mainService.service.Commands;
import com.example.rest.mainService.service.DataBaseCommands;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MyController {

    private DoWork doWork = new DoWork();
    private DataBaseCommands commands = new Commands();

    //home page
    @GetMapping({"/", "/home"})
    public String startPage() {
        return "home";
    }

    //On this page you can paste the URL of the page where the JSON data and header of data, which you will need to download
    @RequestMapping("/writeJsonDataToDatabase")
    public String writeJsonDataToDatabase(Model model, HttpServletRequest request) {
        String info;
        String dataHeader = "";
        String url = "";
        try {
            dataHeader = request.getParameter("dataHeader").trim();
            url = request.getParameter("url").trim();
        } catch (Exception e) {
            //do nothing
        }
        try {
            if (!dataHeader.isEmpty() && !url.isEmpty()) {
                doWork.doWork(url, dataHeader);
                info = "Data added to the database successfully";
                model.addAttribute("message", info);
                return "writeJsonDataToDatabase";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Incorrect data, url or data header not found");
        }
        return "writeJsonDataToDatabase";
    }

    //On this page you can view all downloaded JSON data from database.
    @RequestMapping(value = "/showDataFromDatabase")
    public String showDataFromDatabase(Model model) {
        try {
            List list = commands.getDataFromDatabase();
            if (list.isEmpty()) {
                model.addAttribute("message", "Database is empty !!!");
            } else {
                model.addAttribute("message", list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "showDataFromDatabase";
    }

    //On this page you can search for JSON data by hash
    @GetMapping(value = "/showDataByHash")
    public String showDataByHash(Model model, HttpServletRequest request) {
        String hash;
        try {
            hash = request.getParameter("hash").trim();
            List list = commands.getDataFromDatabaseByHash(hash);
            String[] result = list.get(0).toString().split(",");
            model.addAttribute("message", result);
        }catch (IndexOutOfBoundsException e){
            model.addAttribute("message", "Hash not found !!!");
        } catch (Exception e) {
             e.printStackTrace();
        }
        return "showDataByHash";
    }
}