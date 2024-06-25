package com.jobintechtracking.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jit")
public class hello {

    @GetMapping()
    public  String Hello() {
        return "worked on the port 8040...";
    }
}
