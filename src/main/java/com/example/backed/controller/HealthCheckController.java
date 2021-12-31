package com.example.backed.controller;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class HealthCheckController {

    public static final String URL_CHECK_APPSERVER_HEALTH = "/checkHealth";
    public static final String RESPONSE_RESULT = "success";

    @GetMapping(URL_CHECK_APPSERVER_HEALTH)
    public HealthCheckResult checkHealth() {
        return new HealthCheckResult();
    }

    /**
     * サーバーの稼働チェックのResponse
     */
    @Data
    public static class HealthCheckResult {

        private String result;
        private String checkDatetime;

        public HealthCheckResult() {
            result = RESPONSE_RESULT;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");
            LocalDateTime dt = LocalDateTime.now();
            checkDatetime = LocalDateTime.now().format(dtf);
        }
    }
}
