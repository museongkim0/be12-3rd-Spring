package com.example.package404.instructor.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instructor/homework")
public class HomeworkController {


    @PostMapping("/create")
    public void createHomework() {


    }

    @GetMapping
    public void listHomework() {


    }


//    @GetMapping("/edit")

}
