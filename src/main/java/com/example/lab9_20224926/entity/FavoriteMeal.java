package com.example.lab9_20224926.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "favorite_meals")
public class FavoriteMeal {
    @Id
    @Column(name = "id_meal", nullable = false, length = 50)
    private String idMeal;

    @Column(name = "meal_name", nullable = false, length = 255)
    private String mealName;

    @Column(name = "meal_thumb", nullable = false, length = 255)
    private String mealThumb;

    @Column(name = "category", nullable = false, length = 100)
    private String category;
}