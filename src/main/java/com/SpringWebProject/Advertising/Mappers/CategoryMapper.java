package com.SpringWebProject.Advertising.Mappers;

import com.SpringWebProject.Advertising.Models.Category;
import com.SpringWebProject.Advertising.Models.DTOs.CategoryDTO;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    public CategoryDTO toCategoryDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName());
    }
}
