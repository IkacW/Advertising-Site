package com.SpringWebProject.Advertising.Controllers;

import com.SpringWebProject.Advertising.Models.DTOs.ListingPostDTO;
import com.SpringWebProject.Advertising.Models.DTOs.PaginatedResponseDTO;
import com.SpringWebProject.Advertising.Services.ListingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listings")
public class ListingRestController {
    private final ListingService listingService;

    public ListingRestController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping
    public PaginatedResponseDTO<ListingPostDTO> getAllListings(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
//            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
//            @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction
    ){
        return listingService.getAllListings(pageNo, pageSize);
    }

    @GetMapping("/{id}")
    public ListingPostDTO getListingById(@PathVariable("id") Long id){
        return listingService.getListingById(id);
    }
}
