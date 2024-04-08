package com.shiva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiva.entity.Recipe;
import com.shiva.entity.User;
import com.shiva.service.RecipeServiceImpl;
import com.shiva.service.UserServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

	@Autowired
	private RecipeServiceImpl recipeService;

	@Autowired
	private UserServiceImpl userServiceImpl;


	@PostMapping("/user/{userId}")
	public Recipe createRecipe(@RequestBody Recipe recipe , @PathVariable Long userId) throws Exception {

		User user = userServiceImpl.findUserById(userId);

		Recipe createdRecipe = recipeService.createRecipe(recipe, user);
		return createdRecipe;
	}
	@PutMapping("/{id}")
	public Recipe updateRecipe(@RequestBody Recipe recipe , @PathVariable Long id) throws Exception {

		Recipe updatedRecipe = recipeService.updateRecipe(recipe, id);
		return updatedRecipe;
	}

	@GetMapping()
	public List<Recipe> getAllRecipe(){
		List<Recipe> allRecipes = recipeService.findAllRecipes();
		return allRecipes;
	}

	@DeleteMapping("/{recipeId}")
	public String deleteRecipe(@PathVariable Long recipeId) throws Exception{
		 recipeService.deleteRecipe(recipeId);
		return "Recipe deleted successfully";
	}

	@PutMapping("/{id}/like/user/{userId}")
	public Recipe likeRecipe( @PathVariable Long userId,
			@PathVariable Long id ) throws Exception {

		User user = userServiceImpl.findUserById(userId);

		Recipe updatedRecipe = recipeService.likeRecipe(id, user);
		return updatedRecipe;
	}



}
