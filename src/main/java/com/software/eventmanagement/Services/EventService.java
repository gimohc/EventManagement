package com.software.eventmanagement.Services;

import com.software.eventmanagement.entities.Event;
import com.software.eventmanagement.repositories.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
        @Autowired
        private EventsRepository repository;

        public List<Event> findAll() {
            return repository.findAll();
        }

        public Event save(Event event) {
            return repository.save(event);
        }


}
