package com.SpringWebProject.Advertising.Controllers;

import com.SpringWebProject.Advertising.Models.DTOs.CategoryDTO;
import com.SpringWebProject.Advertising.Repositories.CategoryRepository;
import com.SpringWebProject.Advertising.Services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {
    private final CategoryService service;

    public CategoryRestController(CategoryRepository categoryRepository, CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return service.findAll();
    }
}
