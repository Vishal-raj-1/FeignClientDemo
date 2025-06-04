package com.vishalrajput.service1.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service2")
public interface Service2Client {

    @GetMapping("/service2/api/demo")
    String getDelayedResponse(@RequestParam(name = "delayMs") int delayMs);
}
