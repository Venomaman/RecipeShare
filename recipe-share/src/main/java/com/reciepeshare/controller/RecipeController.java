package com.reciepeshare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.reciepeshare.model.Recipe;
import com.reciepeshare.model.User;
import com.reciepeshare.services.RecipeService;
import com.reciepeshare.services.UserService;

@RestController
public class RecipeController {
	
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/recipe")
	public Recipe createRecipe(@RequestBody Recipe recipe, @RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		Recipe createdRecipe = recipeService.createRecipe(recipe, user);
		return createdRecipe;
	}
	
	@PutMapping("/api/recipe/{id}")
	public Recipe updateRecipe(@RequestBody Recipe recipe,@PathVariable Long id) throws Exception{
		
		
		Recipe updatedRecipe = recipeService.updateRecipe(recipe,id);
		return updatedRecipe;
	}
	
	@GetMapping("/api/recipe")
	public List<Recipe> getAllRecipe()throws Exception{
		List<Recipe> recipe = recipeService.findAllRecipe();
		return recipe;
	}
	
	@DeleteMapping("/api/recipe/{recipeId}")
	public String deleteRecipe(@PathVariable Long recipeId) throws Exception {
		
		recipeService.deleteRecipe(recipeId);
		return "Recipe deleted successfully";
		
	}
	
	@PutMapping("/api/recipe/{id}/like")
	public Recipe likeRecipe(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
		User user = userService.findUserByJwt(jwt);
		
		Recipe likeUpdate = recipeService.likeRecipe(id, user);
		return likeUpdate;
		
	}

}
