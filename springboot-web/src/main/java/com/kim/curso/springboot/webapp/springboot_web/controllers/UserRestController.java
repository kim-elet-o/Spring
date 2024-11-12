package com.kim.curso.springboot.webapp.springboot_web.controllers;

import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kim.curso.springboot.webapp.springboot_web.models.User;
import com.kim.curso.springboot.webapp.springboot_web.models.dto.UserDto;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @GetMapping("/details-map")
    public Map<String, Object> detailsMap()  {
        User user = new User("Ambrosio", "Pollastre");
        Map<String, Object> body = new HashMap<>();

        body.put("title", "Jellouu Uuorld Spring Boot");
        body.put("user", user);

        return body;
    }

    @GetMapping("/details")
    public UserDto details()  {
        User user = new User("Ambrosio", "Pollastre");

        UserDto userDto = new UserDto();
        userDto.setTitle("Jellouu Uuorld Spring Boot");
        userDto.setUser(user);

        return userDto;
    }

    @GetMapping("/list")
    public List<User> list() {
        User user1 = new User("Ambrosio", "Pollastre");
        User user2 = new User("Bimba", "Bose");
        User user3 = new User("Aurora", "Beltran");

        List<User> users = Arrays.asList(user1, user2, user3);

        return users;
    }
}
