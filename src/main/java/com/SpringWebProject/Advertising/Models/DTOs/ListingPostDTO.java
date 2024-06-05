package com.SpringWebProject.Advertising.Models.DTOs;

public record ListingPostDTO(
    Integer id,
    String title,
    String description,
    float price,
    int discount,
//    add category
    UserAuthorDTO authorDTO
) {
}
