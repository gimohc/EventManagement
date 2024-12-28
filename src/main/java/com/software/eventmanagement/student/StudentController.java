package com.software.eventmanagement.student;
import com.software.eventmanagement.Cookies.CookieController;
import com.software.eventmanagement.entities.LoginRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseEntity<?> verifyUser(@RequestBody LoginRequest request, HttpServletResponse response) {
        if (studentService.verifyStudent(request) == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username or password");
        CookieController.setStudentCookie(response, request.getUsername());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/enrollInEvent/{eventId}/")
    public ResponseEntity<?> enrollInEvent(@PathVariable Long eventId, @CookieValue(value= "studentAuthenticationToken") String studentId) {
        studentId = CookieController.getUsernameFromCookie(studentId);
        if(studentService.enrollInEvent(eventId, studentId))
            return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student not found");
    }

    @PostMapping("/cancelEvent/{eventId}")
    public void cancelEvent(@PathVariable Long eventId, @CookieValue(value= "studentAuthenticationToken") String studentId) {
        studentId = CookieController.getUsernameFromCookie(studentId);
        studentService.cancelEnrollment(eventId, studentId);
    }
    @PostMapping("/rate/{eventId}/{rating}")
    public void rateEvent(@PathVariable Long eventId, @CookieValue(value= "studentAuthenticationToken") String studentId, @PathVariable short rating) {
        studentId = CookieController.getUsernameFromCookie(studentId);
        studentService.rateEvent(eventId, studentId, rating);
    }
}