package com.forcode.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

    @PostMapping("getUser")
    public void getUser() {
        System.out.println("============= getUser");
    }

    @PostMapping("test/getUser")
    public void getTestUser() {
        System.out.println("============= getUser");
    }
}
