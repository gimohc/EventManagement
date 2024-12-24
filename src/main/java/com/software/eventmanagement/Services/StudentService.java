package com.software.eventmanagement.Services;

import com.software.eventmanagement.entities.Student;
import com.software.eventmanagement.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> findAll() { return repository.findAll(); }
    public Student save(Student student) { return repository.save(student); }
}
