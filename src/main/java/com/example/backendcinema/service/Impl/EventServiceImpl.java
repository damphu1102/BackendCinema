package com.example.backendcinema.service.Impl;

import com.example.backendcinema.entity.Event;
import com.example.backendcinema.repository.EventRepository;
import com.example.backendcinema.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event findById(int eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            return optionalEvent.get();
        }
        return null;
    }
}
