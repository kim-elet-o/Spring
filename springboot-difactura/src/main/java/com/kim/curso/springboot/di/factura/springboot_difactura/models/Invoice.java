package com.kim.curso.springboot.di.factura.springboot_difactura.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@RequestScope
//@JsonIgnoreProperties({"targetSource", "advisors"})
public class Invoice {

    @Autowired
    private Client client;
    @Value("${invoice.description}")
    private String description;
    @Autowired
    @Qualifier("default")
    private List<Item> items;

    public Invoice() {
        System.out.println("**** Creacion del componente ****");
        System.out.println("Client: " + client);
        System.out.println("description: " + description);
    }

    @PostConstruct
    public void init() {
        System.out.println("**** Justo despues de la creacion del componente ****");
        System.out.println("Client.name: " + client.getName());
        System.out.println("description: " + description);
        client.setName(client.getName() + " Pepelui");
        description += " del cliente " + client.getName() + " " + client.getLastname();
        System.out.println("Client.name: " + client.getName());
        System.out.println("description: " + description);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("**** Justo antes que Se destruya el componente ****");
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descripcion) {
        this.description = descripcion;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getTotal() {
        // int total = 0;
        // for (Item item : items) {
        //     total += item.getAmount();
        // }
        // return total;
        return items.stream().map(item -> item.getAmount()).reduce(0, (sum, amount) -> sum + amount);
    }

}
