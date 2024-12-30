package com.ezbob.log.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/log")
public class LogController {

    @PostMapping
    public void logRequest(@RequestBody String request) {
        System.out.println("Log: " + request);
    }
}