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
}