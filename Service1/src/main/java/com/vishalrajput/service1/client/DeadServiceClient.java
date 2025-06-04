package com.vishalrajput.service1.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "deadService", url = "http://10.255.255.1") // non-routable IP
public interface DeadServiceClient {
    @GetMapping("/api/test")
    String callDeadService();
}


