package com.example.lab9_20224926.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class CategoryController {

    @GetMapping("/categorias")
    public String obtenerCategorias(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.themealdb.com/api/json/v1/1/categories.php";
        String respuesta = restTemplate.getForObject(url, String.class);
        model.addAttribute("categorias", respuesta);
        return "categorias";
    }
}