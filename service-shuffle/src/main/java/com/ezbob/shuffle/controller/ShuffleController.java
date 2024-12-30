package com.ezbob.shuffle.controller;

import com.ezbob.shuffle.service.ShuffleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/shuffle")
public class ShuffleController {
    @Autowired
    private ShuffleService shuffleService;

    @Value("${service-log.base-url}")
    private String logServiceURL;

    private final RestTemplate restTemplate = new RestTemplate();


    @PostMapping
    public ResponseEntity<List<Integer>> shuffleNumbers(@RequestParam int number) {
        if (number < 1 || number > 1000) {
            restTemplate.postForObject(logServiceURL, ResponseEntity.badRequest().body(ResponseEntity.badRequest().build()), Void.class);

            return ResponseEntity.badRequest().build();
        }
        List<Integer> shuffledNumbers = shuffleService.shuffleAndLog(number);
        return ResponseEntity.ok(shuffledNumbers);
    }


}
