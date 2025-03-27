package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/operadores")
public class AdminOperadoresController {

    @GetMapping("/")
    public String mostrarOperadores(Model model) {
        return "adminOperadoresWindow";
    }
}
