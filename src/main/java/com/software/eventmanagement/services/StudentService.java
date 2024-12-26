package com.software.eventmanagement.services;

import com.software.eventmanagement.entities.Student;
import com.software.eventmanagement.repositories.StudentsRepository;
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
