package com.example.rest.controller;

import com.example.rest.mainService.service.DoWork;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyController {

    private DoWork doWork = new DoWork();

    @GetMapping({"/", "/home"})
    public String startPage(){
        return "home";
    }

    @RequestMapping("/writeJsonDataToDatabase")
    public String writeJsonDataToDatabase(Model model, HttpServletRequest request){
        String info;
        String dataHeader = "";
        String url = "";
        try {
            dataHeader = request.getParameter("dataHeader").trim();
            url = request.getParameter("url").trim();
        }catch (Exception e){
            //do nothing
        }
        try {
            if (!dataHeader.isEmpty() && !url.isEmpty()) {
                doWork.doWork(url, dataHeader);
                info = "Data added to the database successfully";
                model.addAttribute("message", info);
                return "writeJsonDataToDatabase";
            }
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errorMessage","Incorrect data, url or data header not found");
        }
        return "writeJsonDataToDatabase";
    }

    @GetMapping("/showDataFromDatabase")
    public String showDataFromDatabase(){
        return "showDataFromDatabase";
    }
}
