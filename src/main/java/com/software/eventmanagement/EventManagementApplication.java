package com.software.eventmanagement;

import com.software.eventmanagement.entities.Student;
import com.software.eventmanagement.services.EventService;
import com.software.eventmanagement.services.StudentService;
import com.software.eventmanagement.services.UserService;
import com.software.eventmanagement.entities.Event;
import com.software.eventmanagement.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
public class EventManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventManagementApplication.class, args);
    }

}
@RestController
@RequestMapping("/users")
class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }
}

@RestController
@RequestMapping("/students")
class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {
        Student student = studentService.findById(id);
        if(student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }
}

@RestController
@RequestMapping("/events")
class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.findById(id);
        if (event != null) {
            return ResponseEntity.ok(event);
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.save(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        Event updatedEvent = eventService.edit(id, eventDetails);
        if (updatedEvent != null) {
            return ResponseEntity.ok(updatedEvent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        boolean deleted = eventService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}



