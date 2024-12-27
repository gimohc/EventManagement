package com.software.eventmanagement.student;

import com.software.eventmanagement.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentsRepository repository;

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

}
