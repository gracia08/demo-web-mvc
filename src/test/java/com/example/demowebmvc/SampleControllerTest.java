package com.example.demowebmvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void helloTest() throws Exception {
        mockMvc.perform(get("/hello")
//                        .header(HttpHeaders.FROM, "localhost")
                )
                .andDo(print())
                .andExpect(status().isOk())
//                .andExpect(content().string("hello"))
//                .andExpect(status().isMethodNotAllowed())

                ;

    }

    @Test
    public void getEvent() throws Exception {
        mockMvc.perform(get("/events/1;name=test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
        ;
    }

    @Test
    public void psotEvents() throws Exception {
        ResultActions result = mockMvc.perform(post("/events")
                        .param("name", "test")
                        .param("limit", "-10"))
                .andDo(print())
                .andExpect(status().isOk())
//                .andExpect(jsonPath("name").value("test"))
                .andExpect(model().hasErrors());
        ModelAndView mav = result.andReturn().getModelAndView();
        Map<String, Object> model = mav.getModel();
        System.out.println(model.size());

    }

}