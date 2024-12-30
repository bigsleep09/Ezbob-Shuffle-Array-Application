package com.ezbob.shuffle.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ShuffleService {
    @Value("${service-log.base-url}")
    private String logServiceURL;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Integer> shuffleAndLog(int number) {
        // Generate shuffled array
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        restTemplate.postForObject(logServiceURL, "Shuffled Array: " + numbers, Void.class);
        // Log the request
        String logMessage = "Shuffled numbers for input: " + number;
        restTemplate.postForObject(logServiceURL, logMessage, Void.class);

        return numbers;
    }
}
