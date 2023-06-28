package com.kyki.taskmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TaskMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskMicroserviceApplication.class, args);
    }

}
