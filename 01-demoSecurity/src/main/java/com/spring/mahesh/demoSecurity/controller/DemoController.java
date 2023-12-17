package com.spring.mahesh.demoSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String sayHello(){
        return "hello";
    }
    @GetMapping("/leaders")
    public String showLeaders(){
        return "leaders";
    }
}
