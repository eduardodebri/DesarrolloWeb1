package com.example.demo.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hanami")
public class HanamiController {

    @GetMapping
    public String mostrarInicio() {
        return "index"; // Carga index.html
    }
}
