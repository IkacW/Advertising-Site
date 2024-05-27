package com.SpringWebProject.Advertising.Repositories;

import com.SpringWebProject.Advertising.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
