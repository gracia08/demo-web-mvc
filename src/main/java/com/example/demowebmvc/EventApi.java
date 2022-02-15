package com.example.demowebmvc;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
public class EventApi {

    public Event createEvent(@RequestBody Event event) {
        // save event
        return event;
    }

    
}
