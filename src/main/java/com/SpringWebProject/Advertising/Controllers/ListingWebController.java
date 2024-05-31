package com.SpringWebProject.Advertising.Controllers;

import com.SpringWebProject.Advertising.Models.DTOs.ListingPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class ListingWebController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String listing(Model model) {
        final String url = "http://localhost:8080/api/listings";
        ResponseEntity<List<ListingPostDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ListingPostDTO>>() {}
                );
        List<ListingPostDTO> listings = response.getBody();
        model.addAttribute("listings", listings);
        return "Listings/index";
    }
}
