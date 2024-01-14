package com.reciepeshare.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reciepeshare.model.Recipe;

@Repository
public interface RecipeRepo extends JpaRepository<Recipe,Long> {

}
