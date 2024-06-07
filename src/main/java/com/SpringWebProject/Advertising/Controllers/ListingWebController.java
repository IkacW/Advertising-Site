package com.SpringWebProject.Advertising.Controllers;

import com.SpringWebProject.Advertising.Models.DTOs.CategoryDTO;
import com.SpringWebProject.Advertising.Models.DTOs.ListingDTO;
import com.SpringWebProject.Advertising.Models.DTOs.ListingPostDTO;
import com.SpringWebProject.Advertising.Models.DTOs.PaginatedResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ListingWebController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/listings")
    public String listing(
            Model model,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
//            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
//            @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction

    ) {
        final String url = "http://localhost:8080/api/listings?pageNo=" + pageNo + "&pageSize=" + pageSize;
        ResponseEntity<PaginatedResponseDTO<ListingDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PaginatedResponseDTO<ListingDTO>>() {}
                );
        PaginatedResponseDTO<ListingDTO> paginatedResponseDTO = response.getBody();

        model.addAttribute("listings", paginatedResponseDTO.content());
        model.addAttribute("pageNo", paginatedResponseDTO.pageNo());
        model.addAttribute("pageSize", paginatedResponseDTO.pageSize());
        model.addAttribute("totalPages", paginatedResponseDTO.totalPages());
        return "Listings/index";
    }

    @GetMapping("listings/{id}")
    public String showListing(
            @PathVariable("id") Integer id,
            Model model
    ) {
        final String url = "http://localhost:8080/api/listings/" + id;

        ResponseEntity<ListingDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ListingDTO>() {}
        );

        ListingDTO listingPostDTO = response.getBody();

        model.addAttribute("listing", listingPostDTO);

        return "Listings/show";
    }

    @GetMapping("/listings/create")
    public String createListing(
            Model model
    ){
        final String url = "http://localhost:8080/api/categories";
        ResponseEntity<List<CategoryDTO>> category = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CategoryDTO>>() {}
        );
        List<CategoryDTO> categoryDTOs = category.getBody();

        model.addAttribute("categories", categoryDTOs);
        return "Listings/create";
    }
}
