
CREATE DATABASE IF NOT EXISTS telemeal_db;
USE telemeal_db;


CREATE TABLE favorite_meals (
    id_meal VARCHAR(50) PRIMARY KEY,
    meal_name VARCHAR(255) NOT NULL,
    meal_thumb VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL
);

select * from favorite_meals;