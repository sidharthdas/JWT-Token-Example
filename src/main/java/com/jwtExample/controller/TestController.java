package com.jwtExample.controller;

import com.jwtExample.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
public class TestController {

    @GetMapping
    public Student getStudent() {
        return new Student(null, "Sidh", null);
    }
}
