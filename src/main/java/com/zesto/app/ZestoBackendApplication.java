package com.zesto.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZestoBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZestoBackendApplication.class, args);
        System.out.println("🚀 Zesto Spring Boot Server running on http://localhost:5000");
    }
}
