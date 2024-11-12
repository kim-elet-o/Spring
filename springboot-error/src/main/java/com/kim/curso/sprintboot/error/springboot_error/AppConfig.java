package com.kim.curso.sprintboot.error.springboot_error;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kim.curso.sprintboot.error.springboot_error.models.domain.User;

@Configuration
public class AppConfig {

    @Bean
    List<User> users() {
        return Arrays.asList(
            new User(1L, "Jose", "Mota"),
            new User(2L, "Federico", "Farfollas"),
            new User(3L, "Amancio", "Pihas"),
            new User(4L, "Josefina", "finoles"),
            new User(5L, "Abby", "Road")
        );
    }

}
