package com.tpe.service;


import com.tpe.domain.Student;
import com.tpe.exceptions.ConflictException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    //getAll()*******************************************************
    public List<Student> getAll() {
        return studentRepository.findAll();
        //Don't need to control anything, getAll has no handle cases
    }


    public void createStudent(Student student) {
        //Check if email is unique

        if (studentRepository.existsByEmail(student.getEmail())){
            throw new ConflictException("Email already exists");
        }

        studentRepository.save(student);

    }

}
