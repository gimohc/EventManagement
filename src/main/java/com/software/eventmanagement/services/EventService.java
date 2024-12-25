package com.software.eventmanagement.services;

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
        public Event findById(long id) {
            if(repository.findById(id).isPresent())
                return repository.findById(id).get();
            return null;
        }
        public boolean delete(long id) {
            if(repository.findById(id).isEmpty()) {
                repository.deleteById(id);
                return true;
            }
            return false;
        }
        public Event edit(long id, Event newEventDetails) {
            if(repository.findById(id).isPresent()) {
                Event newEvent = new Event(id, newEventDetails);
                repository.save(newEvent);
                return newEvent;
            }
            return null;
        }


}
