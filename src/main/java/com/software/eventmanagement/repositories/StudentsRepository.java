package com.software.eventmanagement.repositories;

import com.software.eventmanagement.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Student, Integer> {

}
