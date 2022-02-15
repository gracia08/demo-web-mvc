package com.example.demowebmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.PushBuilder;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SampleController {

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }


    @GetMapping("/events")
    @ResponseBody
    public String events(PushBuilder pushBuilder) {
        pushBuilder.getMethod();
        return "events";
    }

    @GetMapping("/events/{id}")
    @ResponseBody
    public Event getEvents(@PathVariable Integer id, @MatrixVariable String name) {
        Event event = new Event();
        event.setId(id);
        event.setName(name);
        return event;
    }



    @GetMapping("/events/form")
    public String eventsForm(Model model) {
        Event event = new Event();
        model.addAttribute("event", event);
        return "/events/form";
    }

    @PostMapping("/events")
    public String getPostEvents(@Validated @ModelAttribute Event event,
                                BindingResult bindingResult,
                                RedirectAttributes attributes) {
        if(bindingResult.hasErrors()) {
            return "/events/form";
        }

        attributes.addAttribute("name", event.getName());
        return "redirect:/events/list";
    }

    @GetMapping("/events/list")
    public String getEvents(Model model) {
        Event event = new Event();
        event.setName("spring");
        event.setLimit(10);

        List<Event> eventList = new ArrayList<>();
        eventList.add(event);
        model.addAttribute(eventList);

        ResponseEntity.badRequest().build();

        return "/events/list";
    }


}
