package com.example.practicatfgbd.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LogController {

    @PostMapping("hola")
    public String login(){

        return "adios";
    }
}
