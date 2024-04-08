package com.shiva.service;

import java.util.List;

import com.shiva.entity.Recipe;
import com.shiva.entity.User;

public interface RecipeInterface {

	public Recipe createRecipe(Recipe recipe, User user);

	public Recipe findRecipeById(Long id) throws Exception;

	public void deleteRecipe (Long id) throws Exception;

	public Recipe updateRecipe(Recipe recipe, Long id) throws Exception;

	public List<Recipe> findAllRecipes();

	public Recipe likeRecipe(Long recipeId, User user) throws Exception;

}
