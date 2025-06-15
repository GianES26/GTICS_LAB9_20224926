package com.example.lab9_20224926.controller;

import com.example.lab9_20224926.entity.FavoriteMeal;
import com.example.lab9_20224926.repository.FavoriteMealRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
public class CategoryController {

    @Autowired
    private FavoriteMealRepository favoriteMealRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/categorias")
    public String obtenerCategorias(Model model) {
        String url = "https://www.themealdb.com/api/json/v1/1/categories.php";
        String respuesta = restTemplate.getForObject(url, String.class);
        try {
            Map<String, Object> data = objectMapper.readValue(respuesta, Map.class);
            model.addAttribute("categorias", data);
        } catch (Exception e) {
            model.addAttribute("error", "Error al conectar con la API (Código 522). Intente de nuevo más tarde.");
        }
        model.addAttribute("favoritas", favoriteMealRepository.findAll());
        return "categorias";
    }
}