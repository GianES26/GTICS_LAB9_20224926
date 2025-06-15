package com.example.lab9_20224926.repository;

import com.example.lab9_20224926.entity.FavoriteMeal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteMealRepository extends JpaRepository<FavoriteMeal, String> {
}