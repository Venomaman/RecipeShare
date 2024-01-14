package com.reciepeshare.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reciepeshare.model.Recipe;
import com.reciepeshare.model.User;
import com.reciepeshare.repo.RecipeRepo;

@Service
public class RecipeServiceImplementation implements RecipeService{
	
	@Autowired
	private RecipeRepo recipeRepo;

	@Override
	public Recipe createRecipe(Recipe recipe, User user) {
		
		Recipe createRecipe = new Recipe();
		createRecipe.setTitle(recipe.getTitle());
		createRecipe.setImage(recipe.getImage());
		createRecipe.setDescription(recipe.getDescription());
		createRecipe.setVegetarian(recipe.getVegetarian());
		createRecipe.setLikes(recipe.getLikes());
		createRecipe.setCreatedAt(LocalDateTime.now());
		createRecipe.setUser(user);
		
		return recipeRepo.save(createRecipe);
	}

	@Override
	public Recipe findRecipeByid(Long id) throws Exception {
		Optional<Recipe> findRecipe = recipeRepo.findById(id);
		if(findRecipe.isPresent()) {
			return findRecipe.get();
		}
		else {
			throw new Exception("recipe not found with id "+id);
		}
		
	}

	@Override
	public void deleteRecipe(Long id) throws Exception {
		findRecipeByid(id);
		recipeRepo.deleteById(id);
		
	}

	@Override
	public Recipe updateRecipe(Recipe recipe, Long id) throws Exception {
		Recipe oldRecipe=findRecipeByid(id);
		if(recipe.getTitle()!=null) {
			oldRecipe.setTitle(recipe.getTitle());
		}
		if(recipe.getDescription()!=null) {
			oldRecipe.setDescription(recipe.getDescription());
		}
		if(recipe.getImage()!=null) {
			oldRecipe.setImage(recipe.getImage());
		}
		
		return recipeRepo.save(oldRecipe);
	}

	@Override
	public List<Recipe> findAllRecipe() {
		
		return recipeRepo.findAll();
	}

	@Override
	public Recipe likeRecipe(Long recipeId, User user) throws Exception {
		
		Recipe recipe = findRecipeByid(recipeId);
		
		if(recipe.getLikes().contains(user.getId())) {
			recipe.getLikes().remove(user.getId());
		}else {
			recipe.getLikes().add(user.getId());
		}
		
		return recipeRepo.save(recipe);
		
	}

}
