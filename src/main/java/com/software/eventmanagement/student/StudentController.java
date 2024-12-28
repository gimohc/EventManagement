package com.software.eventmanagement.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable String id) {
        Student student = studentService.findById(id);
        if(student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }
    @PostMapping("/verifyStudent")
    public ResponseEntity<?> verifyUser(@RequestBody Student student) {
        if (studentService.verifyStudent(student) == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        return ResponseEntity.ok(studentService.verifyStudent(student));
    }
    @PostMapping("/enrollInEvent/{eventId}/{studentId}")
    public ResponseEntity<?> enrollInEvent(@PathVariable Long eventId, @PathVariable String studentId) {
        if(studentService.enrollInEvent(eventId, studentId))
            return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student not found");
    }
    @PostMapping("/cancelEvent/{eventId}/{studentId}")
    public void cancelEvent(@PathVariable Long eventId, @PathVariable String studentId) {
        studentService.cancelEnrollment(eventId, studentId);
    }
    @PostMapping("/rate/{eventId}/{studentId}/{rating}")
    public void rateEvent(@PathVariable Long eventId, @PathVariable String studentId, @PathVariable short rating) {
        studentService.rateEvent(eventId, studentId, rating);
    }
}