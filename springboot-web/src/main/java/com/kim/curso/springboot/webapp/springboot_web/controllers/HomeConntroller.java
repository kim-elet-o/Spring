package com.kim.curso.springboot.webapp.springboot_web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeConntroller {

    // si se accede a cualquier subUrl del @GetMapping se redirige a /list cambiando la url.
    @GetMapping({"", "/", "/home"})
    public String home() {
        return "redirect:/details";
        // forward redirige a list sin cambiar la url.
        // return "forward:/details";
    }
    

}
