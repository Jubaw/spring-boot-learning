package com.tpe.controller;


import com.tpe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register") // http://localhost:8080/register
public class UserController {


    @Autowired
    private UserService userService;


    //Note: Register
    @PostMapping // http://localhost:8080/register + POST + JSON
    public ResponseEntity
}
