package com.SpringWebProject.Advertising.Services;

import com.SpringWebProject.Advertising.Mappers.CategoryMapper;
import com.SpringWebProject.Advertising.Models.Category;
import com.SpringWebProject.Advertising.Models.DTOs.CategoryDTO;
import com.SpringWebProject.Advertising.Repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toCategoryDTO)
                .toList();
    }

    public Category find(int id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
