package com.example.demo.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(Model model) {
        model.addAttribute("mensaje", "Lo sentimos, ocurrió un error.");
        return "error"; // Nombre de la plantilla Thymeleaf (error.html)
    }

    public String getErrorPath() {
        return "/error";
    }


}
