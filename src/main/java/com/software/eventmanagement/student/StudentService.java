package com.software.eventmanagement.student;

import com.software.eventmanagement.event.Event;
import com.software.eventmanagement.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentsRepository repository;
    @Autowired
    private EventService eventService;

    public Student findById(String id) {
        if(repository.findById(id).isPresent())
            return repository.findById(id).get();
        return null;
    }
    public Student verifyStudent(Student student) {
        if(repository.findById(student.getUsername()).isPresent()) {
            Student found = repository.findById(student.getUsername()).get();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(passwordEncoder.matches(student.getPassword(), found.getPassword()))
                return found;
        }
        return null;
    }
    public boolean enrollInEvent(Long eventId, String studentId) {
        Student student = findById(studentId);
        if(student == null)
            return false;
        Event event = eventService.findById(eventId);
        if(event == null || event.getSeats() >= event.getParticipants())
            return false;
        event.setParticipants((short) (event.getParticipants()+1));
        eventService.save(event);
        student.getEvents().add(eventId);
        repository.save(student);
        return true;
    }
    public void cancelEnrollment(Long eventId, String studentId) {
        Student student = findById(studentId);
        if(student == null || studentNotEnrolled(eventId, studentId))
            return;
        Event event = eventService.findById(eventId);
        event.setParticipants((short) (event.getParticipants()-1));
        eventService.save(event);
        student.getEvents().remove(eventId);
        repository.save(student);
    }
    public void rateEvent(Long eventId, String studentId, short rating) {
        Student student = findById(studentId);
        Event event = eventService.findById(eventId);

        if(student == null || event == null || studentNotEnrolled(eventId, studentId))
            return;
        short oldRatings = event.getRating();
        short numRatings = event.getNumRatings();
        rating = (short)((oldRatings * numRatings + rating) / (numRatings + 1));
        event.setRating(rating);
        event.setNumRatings((short)(numRatings+1));
        eventService.save(event);
    }
    public boolean studentNotEnrolled(Long eventId, String studentId) {
        Student student = findById(studentId);
        if(student == null)
            return true;
        List<Long> studentEvents = student.getEvents();
        return !studentEvents.contains(eventId);
    }
}
