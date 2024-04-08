package com.shiva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shiva.entity.Recipe;
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
