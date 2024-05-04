package com.tpe.controller;


import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController//Specialized controller for REST architecture.
//Why not @Component(lets you do injection)  ? A: Because it wouldnt direct requests to DispatcherServlet.
@RequestMapping("/students") // htttp://localhost:8080/students, means if a request comes --
// with a endpoint like this,it will be welcomed by students
//More developed version of MVC
public class StudentController {//http://localhost:8080/students + GET + PUT + POST + DELETE All will be directed to this class

    @Autowired
    private StudentService studentService;


    //getAll()*******************************************************
    @GetMapping // http://localhost:8080/students + GET
    // 1 --> Student[] olur mu ? Olmaz List<> ile calismamam gerekiyor
    // 2 --> Response icinde Status codunu rahat setlemek icin ResponseEntity ..
    public ResponseEntity<List<Student>> getAll() {
        List<Student> students = studentService.getAll();
        return ResponseEntity.ok(students); //Student objects has been sent to client with 200 status code.
        //        return new ResponseEntity<>(students, HttpStatus.OK);
    }


    //createStudent()*******************************************************
    @PostMapping // http://localhost:8080/students + POST + JSON
    public ResponseEntity<Map<String, String>> createStudent(@Valid @RequestBody Student student) { //JSON ---> Student
        // @Valid : parametreler valid mi kontrol eder, bu örenekte Student
        //objesi olusturmak için gönderilen fieldlar yani //name gibi özellikler düzgün set edilmis mi ona bakar.
        // @RequestBody = gelen requestin bodysindeki bilgiyi ,
        //Student objesine map edilmesini sagliyor.
        studentService.createStudent(student);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Student is created successfully");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    //Not: getStudentById() with RequestParam *******************************************************
    //For getting data with multiple queries like "GET ME STUDENT WITH THIS.ID AND IN THIS.CITY"
    @GetMapping("/query") //http://localhost:8080/students/query?id=1 + GET
    public ResponseEntity<Student> getStudent(@RequestParam("id") Long id) {
        Student student = studentService.findStudent(id);
        return ResponseEntity.ok(student); //200
    }

    //Not: getStudentById()  with PathVariable *******************************************************
    @GetMapping("/{id}") //http://localhost:8080/students/1 + GET
    public ResponseEntity<Student> getStudentByPath(@PathVariable("id") Long id) {
        Student student = studentService.findStudent(id);
        return ResponseEntity.ok(student);
    }

    // !!! TRICK = 1 data alacaksam PathVariable ama birden fazla data alacaksam
    //  RequestParam daha kullanisli

    //Not: deleteStudent() *******************************************************
    //http://localhost:8080/students/1  + DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Student is deleted successfully");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.OK); //return ResponseEntity.ok(map);
    }

    //Not: updateStudent() *******************************************************
    @PutMapping("/{id}") //http://localhost:8080/students/1 + PUT + JSON
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        studentService.updateStudent(id, studentDTO);
        String message = "Student is updated successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);//200

    }


        /*
        ***** SORU-1 :  @Controller yerine , @Component kullanirsam ne olur ??
        **    CEVAP-1 : Dispatcher , @Controller ile annote edilmis sınıfları tarar ve
        bunların içindeki @RequestMapping annotationlari algilamaya calisir. Dikkat :
        @Component ile annote edilen siniflar taranmayacaktir..
        Ayrica  @RequestMapping'i yalnızca sınıfları @Controller ile annote edilmis olan
        methodlar üzerinde/içinde kullanabiliriz ve @Component, @Service, @Repository vb.
        ile ÇALIŞMAZ…
        ***** SORU-2 : @RestController ile @Controller arasindaki fark nedir ??
        **   CEVAP-2 : @Controller, Spring MVC framework'ünün bir parçasıdır.genellikle HTML
        sayfalarının görüntülenmesi veya yönlendirilmesi gibi işlevleri gerçekleştirmek
        üzere kullanılır.
                       @RestController annotation'ı, @Controller'dan türetilmiştir ve RESTful
         web servisleri sağlamak için kullanılır.Bir sınıfın üzerine konulduğunda, tüm
         metodlarının HTTP taleplerine JSON gibi formatlarda cevap vermesini sağlar.
         ***** SORU-3 : Controller'dan direk Repo ya gecebilir miyim
         **   CEVAP-3: HAYIR, BusinessLogic ( kontrol ) katmani olan Service'i atlamam gerekir.
 */


}
