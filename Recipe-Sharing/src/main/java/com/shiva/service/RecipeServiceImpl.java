package com.shiva.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiva.entity.Recipe;
import com.shiva.entity.User;
import com.shiva.repository.RecipeRepository;
@Service
public class RecipeServiceImpl implements RecipeInterface{

	@Autowired
	private RecipeRepository recipeRepository;

	@Override
	public Recipe createRecipe(Recipe recipe, User user) {

		Recipe createdRecipe = new Recipe();

		createdRecipe.setTitle(recipe.getTitle());
		createdRecipe.setImage(recipe.getImage());
		createdRecipe.setDescription(recipe.getDescription());
		createdRecipe.setUser(user);
		createdRecipe.setCreatedAt(LocalDateTime.now());

		return recipeRepository.save(createdRecipe);
	}

	@Override
	public Recipe findRecipeById(Long id) throws Exception {
		Optional<Recipe> optional = recipeRepository.findById(id);

		if(optional.isPresent()) {
			return optional.get();
		}
		throw new Exception("Recipe not found with "+id);

	}

	@Override
	public void deleteRecipe(Long id) throws Exception {
		findRecipeById(id);

		recipeRepository.deleteById(id);

	}

	@Override
	public Recipe updateRecipe(Recipe recipe, Long id) throws Exception {

		Recipe oldRecipe = findRecipeById(id);

		if(recipe.getTitle() != null) {
			oldRecipe.setTitle(recipe.getTitle());
		}
		if(recipe.getImage() != null) {
			oldRecipe.setImage(recipe.getImage());
		}
		if(recipe.getDescription() != null) {
			oldRecipe.setDescription(recipe.getDescription());
		}

		return recipeRepository.save(oldRecipe);
	}

	@Override
	public List<Recipe> findAllRecipes() {
		return recipeRepository.findAll();
	}

	@Override
	public Recipe likeRecipe(Long recipeId, User user) throws Exception {

		Recipe recipe = findRecipeById(recipeId);

		if(recipe.getLikes().contains(user.getId())) {
			recipe.getLikes().remove(user.getId());
		} else {
			recipe.getLikes().add(user.getId());
		}
		return recipeRepository.save(recipe);
	}
}
