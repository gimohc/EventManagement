package com.software.eventmanagement.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentsRepository repository;
    public Student findById(int id) {
        if(repository.findById(id).isPresent())
            return repository.findById(id).get();
        return null;
    }

}
