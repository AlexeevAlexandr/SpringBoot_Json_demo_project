package com.example.rest;

import com.example.rest.controller.MyController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    HttpServletRequest request;

    @Mock
    Model model;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testStartPage() throws Exception {
        this
                .mockMvc.perform(get("/","home")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("home")));
    }

    @Test
    public void testWriteJsonDataToDatabase() throws Exception {
        when(request.getParameter("dataHeader")).thenReturn("dataHeader");
        when(request.getParameter("url")).thenReturn("url");

        MyController myServlet =new MyController();
        myServlet.writeJsonDataToDatabase(model, request);

        this
            .mockMvc.perform(get("/","writeJsonDataToDatabase")).andDo(print()).andExpect(status().isOk())
            .andExpect(content().string(containsString("writeJsonDataToDatabase")));

    }

    @Test
    public void testShowDataByHash() throws Exception {
        when(request.getParameter("hash")).thenReturn("hash");
        MyController myServlet =new MyController();
        myServlet.showDataByHash(model, request);
        this
            .mockMvc.perform(get("/","showDataByHash")).andDo(print()).andExpect(status().isOk())
            .andExpect(content().string(containsString("showDataByHash")));

    }

    @Test
    public void testShowDataFromDatabase() throws Exception {
        MyController myServlet =new MyController();
        myServlet.showDataFromDatabase(model);
        this
            .mockMvc.perform(get("/","showDataFromDatabase")).andDo(print()).andExpect(status().isOk())
            .andExpect(content().string(containsString("showDataFromDatabase")));

    }
}
