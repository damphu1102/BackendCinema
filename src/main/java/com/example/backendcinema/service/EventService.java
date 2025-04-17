package com.example.backendcinema.service;

import com.example.backendcinema.entity.Event;

import java.util.List;

public interface EventService {
    List<Event> getAll();

    Event findById(int eventId);
}
