package com.example.backendcinema.controller;

import com.example.backendcinema.entity.Event;
import com.example.backendcinema.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/event")
@CrossOrigin("*")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getAllEvents(){
        return eventService.getAll();
    }

    @GetMapping("/{eventId}")
    public Event findById(@PathVariable int eventId){
        return eventService.findById(eventId);
    }
}
