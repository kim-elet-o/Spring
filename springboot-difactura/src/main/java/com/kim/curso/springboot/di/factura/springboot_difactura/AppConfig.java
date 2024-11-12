package com.kim.curso.springboot.di.factura.springboot_difactura;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.kim.curso.springboot.di.factura.springboot_difactura.models.Item;
import com.kim.curso.springboot.di.factura.springboot_difactura.models.Product;

@Configuration
@PropertySource(value="classpath:data.properties", encoding="UTF-8")
public class AppConfig {

    // @Primary
    @Bean("default")
    List<Item> itemsInvoice() {
        return Arrays.asList(
            new Item(new Product("Trampa para chupacabras", 10), 2),
            new Item(new Product("Airganboy sin piernas", 15), 5),
            new Item(new Product("Chorizo de plastelina", 25), 11)
        );
    }

    @Bean
    List<Item> itemsInvoiceOffice() {
        return Arrays.asList(
            new Item(new Product("Kit ojos abiertos", 16), 3),
            new Item(new Product("1100 clips de humus", 17), 2),
            new Item(new Product("grapadora con grapas de turron", 28), 6)
        );
    }

}
