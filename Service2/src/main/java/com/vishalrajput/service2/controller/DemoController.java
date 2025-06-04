package com.vishalrajput.service2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service2/api")
public class DemoController {

    @GetMapping("/demo")
    public String demo(@RequestParam(defaultValue = "3000") int delayMs) throws InterruptedException{
        System.out.println("Fetching data from third party");
        Thread.sleep(delayMs);
        return "Hello from Service 2";
    }
}
