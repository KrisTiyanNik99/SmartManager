package com.example.smart_manager.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/products.html")
    public String getProduct() {
        return "products.html";
    }

    @GetMapping("/products")
    public String getProductsss() {
        return "products.html";
    }

    @GetMapping("/register.html")
    public String getProductss() {
        return "register.html";
    }

    @GetMapping("/product-form.html")
    public String getProducts() {
        return "product-form.html";
    }

    @GetMapping("/cart.html")
    public String getCart() {
        return "cart.html";
    }

    @GetMapping("/tasks.html")
    public String getTask() {
        return "tasks.html";
    }

    @GetMapping("/groups")
    public String getGroupo() {
        return "groups.html";
    }

    @GetMapping("/group-form.html")
    public String getGroupos() {
        return "group-form.html";
    }
}
