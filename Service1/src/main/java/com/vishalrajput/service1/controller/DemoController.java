package com.vishalrajput.service1.controller;

import com.vishalrajput.service1.client.Service2Client;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/service1/api/")
public class DemoController {
    private final Service2Client service2Client;

    @GetMapping("/demo")
    public ResponseEntity<String> demo(@RequestParam(defaultValue = "3000") int delayMs) {
        long start = System.currentTimeMillis();
        try{
            String response = service2Client.getDelayedResponse(delayMs);
            long duration = System.currentTimeMillis() - start;
            return ResponseEntity.ok("Success in " + duration + "ms: " + response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                    .body("Request failed or timed out: " + ex.getMessage());
        }
    }

}
