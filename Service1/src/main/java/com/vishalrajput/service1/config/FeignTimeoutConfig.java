package com.vishalrajput.service1.config;

import feign.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class FeignTimeoutConfig {

    @Value("${feign.client.config.default.connectTimeout}")
    private int connectTimeout;

    @Value("${feign.client.config.default.readTimeout}")
    private int readTimeout;

    @Bean
    public Request.Options timeoutOptions() {
        return new Request.Options(connectTimeout, TimeUnit.MILLISECONDS, readTimeout, TimeUnit.MILLISECONDS, true);
    }
}
