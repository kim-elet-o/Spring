package com.kim.curso.springboot.app.aop.springboot_aop.ccontrollers;

import org.springframework.web.bind.annotation.RestController;

import com.kim.curso.springboot.app.aop.springboot_aop.services.GreetingService;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping("/greeting")
    public ResponseEntity<?> greeting() {
        return ResponseEntity.ok(Collections.singletonMap("greeting",
         greetingService.sayHello("Pepelui", "Vete a freir monas")));
    }

    @GetMapping("/greeting-error")
    public ResponseEntity<?> greetingError() {
        return ResponseEntity.ok(Collections.singletonMap("greeting",
         greetingService.sayHelloError("Pepelui", "Vete a freir monas")));
    }

}
