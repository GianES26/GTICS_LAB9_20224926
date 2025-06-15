package com.example.lab9_20224926.controller;

import com.example.lab9_20224926.entity.FavoriteMeal;
import com.example.lab9_20224926.repository.FavoriteMealRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
public class MealController {

    @Autowired
    private FavoriteMealRepository favoriteMealRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/buscar")
    public String buscarComida(@RequestParam String comida, Model model) {
        String url = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + comida;
        String respuesta = restTemplate.getForObject(url, String.class);
        try {
            Map<String, Object> data = objectMapper.readValue(respuesta, Map.class);
            model.addAttribute("comidas", data);
        } catch (Exception e) {
            model.addAttribute("error", "Error al procesar la búsqueda: " + e.getMessage());
        }
        return "resultadosBusqueda";
    }

    @GetMapping("/detalleComida")
    public String obtenerDetalleComida(@RequestParam String id, Model model) {
        String url = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=" + id;
        String respuesta = restTemplate.getForObject(url, String.class);
        try {
            Map<String, Object> data = objectMapper.readValue(respuesta, Map.class);
            model.addAttribute("comida", data);
            // Check if meal is already a favorite
            boolean isFavorite = favoriteMealRepository.findById(id).isPresent();
            model.addAttribute("isFavorite", isFavorite);
        } catch (Exception e) {
            model.addAttribute("error", "Error al procesar los datos de la comida: " + e.getMessage());
        }
        return "detalleComida";
    }

    @PostMapping("/agregarFavorita")
    public String agregarFavorita(@RequestBody FavoriteMeal favoriteMeal) {
        // Check if meal already exists to prevent duplicates
        if (!favoriteMealRepository.findById(favoriteMeal.getIdMeal()).isPresent()) {
            favoriteMealRepository.save(favoriteMeal);
        }
        return "redirect:/detalleComida?id=" + favoriteMeal.getIdMeal();
    }
}