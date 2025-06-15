package com.example.lab9_20224926.controller;

import com.example.lab9_20224926.entity.FavoriteMeal;
import com.example.lab9_20224926.repository.FavoriteMealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class MealController {

    @Autowired
    private FavoriteMealRepository favoriteMealRepository;

    @GetMapping("/buscar")
    public String buscarComida(@RequestParam String comida, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + comida;
        String respuesta = restTemplate.getForObject(url, String.class);
        model.addAttribute("comidas", respuesta);
        return "resultadosBusqueda";
    }

    @GetMapping("/detalleComida")
    public String obtenerDetalleComida(@RequestParam String id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=" + id;
        String respuesta = restTemplate.getForObject(url, String.class);
        model.addAttribute("comida", respuesta);
        return "detalleComida";
    }

    @PostMapping("/agregarFavorita")
    public String agregarFavorita(@RequestBody FavoriteMeal favoriteMeal) {
        favoriteMealRepository.save(favoriteMeal);
        return "redirect:/detalleComida?id=" + favoriteMeal.getIdMeal();
    }
}