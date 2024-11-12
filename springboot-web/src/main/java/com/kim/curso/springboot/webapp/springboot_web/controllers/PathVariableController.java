package com.kim.curso.springboot.webapp.springboot_web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kim.curso.springboot.webapp.springboot_web.models.User;
import com.kim.curso.springboot.webapp.springboot_web.models.dto.ParamDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/var")
public class PathVariableController {

    @Value("${config.code}")
    private Integer code;

    @Value("${config.username}")
    private String username;

    //@Value("${config.message}")
    //private String message;

    @Value("${config.listOfValues}")
    private List<String> listOfValues;

    // En vez que @Value haga la conversion automatica, esta se realice mediante codigo java, ademas de pasar las letras a mayusculas.
    @Value("#{'${config.listOfValues}'.toUpperCase().split(',')}")
    private List<String> valueList;

    // Transforma el listado de strings en un solo String y pone las letras en mayuscula.
    @Value("#{'${config.listOfValues}'.toUpperCase()}")
    private String valueListInString;

    // Se introduce un json en un map.
    @Value("#{${config.valuesMap}}")
    private Map<String, Object> valuesMap;

    // Se obtiene solo el valor del producto que hay en el json.
    @Value("#{${config.valuesMap}.product}")
    private String product;

    // Se obtiene solo el valor del precio que hay en el json.
    @Value("#{${config.valuesMap}.price}")
    private String price;

    @Autowired
    private Environment environment;

    @GetMapping("/baz/{message}")
    public ParamDto baz(@PathVariable String message) {
        ParamDto paramDto = new ParamDto();
        paramDto.setMessage(message);
        return paramDto;
    }
    
    @GetMapping("/mix/{product}/{id}")
    public Map<String, Object> mixPathVar(@PathVariable String product, @PathVariable Long id) {

        Map<String, Object> json = new HashMap<>();
        json.put("product", product);
        json.put("id", id);

        return json;
    }

    @PostMapping("/create")
    public User create(@RequestBody User user) {      
        // Aqui por ejemplo se haria el save del usuario en la BD.
        user.setName(user.getName().toUpperCase());
        return user;
    }
    
    @GetMapping("/values")
    public Map<String, Object> values(@Value("${config.message}") String message) {

        Map<String, Object> json = new HashMap<>();
        json.put("code", code);
        json.put("code2", environment.getProperty("config.code", Long.class));
        json.put("username", username);
        json.put("message", message);
        json.put("message2", environment.getProperty("config.message"));
        json.put("listOfValues", listOfValues);
        json.put("valueList", valueList);
        json.put("valueListInString", valueListInString);
        json.put("valuesMap", valuesMap);
        json.put("product", product);
        json.put("price", valuesMap);

        return json;
    }

}
