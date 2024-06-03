package com.SpringWebProject.Advertising.Controllers;

import com.SpringWebProject.Advertising.Models.DTOs.ListingPostDTO;
import com.SpringWebProject.Advertising.Models.DTOs.PaginatedResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

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
        ResponseEntity<PaginatedResponseDTO<ListingPostDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PaginatedResponseDTO<ListingPostDTO>>() {}
                );
        PaginatedResponseDTO<ListingPostDTO> paginatedResponseDTO = response.getBody();
        System.out.println(paginatedResponseDTO.toString());
        model.addAttribute("listings", paginatedResponseDTO.content());
        model.addAttribute("pageNo", paginatedResponseDTO.pageNo());
        model.addAttribute("pageSize", paginatedResponseDTO.pageSize());
        model.addAttribute("totalPages", paginatedResponseDTO.totalPages());
        return "Listings/index";
    }
}
