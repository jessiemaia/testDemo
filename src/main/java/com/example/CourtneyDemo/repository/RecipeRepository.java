package com.example.CourtneyDemo.repository;

import com.example.CourtneyDemo.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, String> {
    List<Recipe> findAllByOrderByNameAsc();
}
