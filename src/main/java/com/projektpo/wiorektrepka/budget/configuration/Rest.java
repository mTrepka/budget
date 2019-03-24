package com.projektpo.wiorektrepka.budget.configuration;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rest {

    @GetMapping("/")
    String hello(){
        return "hello world";
    }
}
