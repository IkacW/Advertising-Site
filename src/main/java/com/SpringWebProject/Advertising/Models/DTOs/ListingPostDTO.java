package com.SpringWebProject.Advertising.Models.DTOs;

public record ListingPostDTO (
        String title,
        String description,
        float price,
        int category
) {
}
