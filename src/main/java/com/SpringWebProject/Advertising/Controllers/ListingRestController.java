package com.SpringWebProject.Advertising.Controllers;

import com.SpringWebProject.Advertising.Models.DTOs.ListingPostDTO;
import com.SpringWebProject.Advertising.Services.ListingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/listings")
public class ListingRestController {
    private final ListingService listingService;

    public ListingRestController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping
    public List<ListingPostDTO> getAllListings(){
        return listingService.getAllListings();
    }
}
