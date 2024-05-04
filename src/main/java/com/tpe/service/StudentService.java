package com.tpe.service;


import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.exceptions.ConflictException;
import com.tpe.exceptions.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

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
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new ConflictException("Email already exists");
        }
        studentRepository.save(student);
    }

    //Not: findStudent() *******************************************************
    public Student findStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Student not found with id: " + id));
    }

    //Not: deleteStudent() *******************************************************
    public void deleteStudent(Long id) {
        Student student = findStudent(id);
        //studentRepository.delete(student);
        studentRepository.deleteById(id);
    }


    public void updateStudent(Long id, StudentDTO studentDTO) {
        //ID exists control
        Student student = findStudent(id);



    }
}
