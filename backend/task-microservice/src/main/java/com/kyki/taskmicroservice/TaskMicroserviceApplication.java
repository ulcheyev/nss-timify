package com.kyki.taskmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TaskMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskMicroserviceApplication.class, args);
    }

}
