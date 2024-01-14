package com.reciepeshare.services;

import java.util.List;

import com.reciepeshare.model.Recipe;
import com.reciepeshare.model.User;

public interface RecipeService {
	
	public Recipe createRecipe(Recipe recipe, User user);
	
	public Recipe findRecipeByid(Long id) throws Exception;
	
	public void deleteRecipe(Long id)throws Exception;
	
	public Recipe updateRecipe(Recipe recipe, Long id) throws Exception;
	
	public List<Recipe> findAllRecipe();
	
	public Recipe likeRecipe(Long recipeId, User user) throws Exception;

}
