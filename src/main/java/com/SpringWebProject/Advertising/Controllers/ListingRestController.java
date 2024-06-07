package com.SpringWebProject.Advertising.Controllers;

import com.SpringWebProject.Advertising.Models.DTOs.ListingDTO;
import com.SpringWebProject.Advertising.Models.DTOs.ListingPostDTO;
import com.SpringWebProject.Advertising.Models.DTOs.PaginatedResponseDTO;
import com.SpringWebProject.Advertising.Models.Listing;
import com.SpringWebProject.Advertising.Models.User;
import com.SpringWebProject.Advertising.Services.CategoryService;
import com.SpringWebProject.Advertising.Services.ListingService;
import com.SpringWebProject.Advertising.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/listings")
public class ListingRestController {
    private static final Logger log = LoggerFactory.getLogger(ListingRestController.class);
    private final ListingService listingService;
    private final CategoryService categoryService;
//    private final UserService userService;

    public ListingRestController(ListingService listingService, CategoryService categoryService, UserService userService) {
        this.listingService = listingService;
        this.categoryService = categoryService;
//        this.userService = userService;
    }

    @GetMapping
    public PaginatedResponseDTO<ListingDTO> getAllListings(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
//            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
//            @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction
    ){
        return listingService.getAllListings(pageNo, pageSize);
    }

    @GetMapping("/{id}")
    public ListingDTO getListingById(@PathVariable("id") Long id){
        return listingService.getListingById(id);
    }

    @PostMapping("/create")
    public RedirectView createListing(
            @ModelAttribute ListingPostDTO listingPostDTO,
            @RequestParam("formFile") MultipartFile image,
            RedirectAttributes redirectAttributes
    ) {
        if(image.isEmpty()) {
            log.error("Image is empty");
            redirectAttributes.addFlashAttribute("message", "Please select an image file");
            return new RedirectView("/listings/create");
        }

        try {
            log.info("Getting image bytes");
            byte[] bytes = image.getBytes();

            Path uploadDir = Paths.get("upload");

            if(!Files.exists(uploadDir)) {
                log.info("Creating upload directory");
                Files.createDirectory(uploadDir);
            }

            log.info("Creating path");
            Path path = uploadDir.resolve(image.getOriginalFilename());
            log.info("Writing image in desired directory");
            Files.write(path, bytes);

            log.info("Saving listing");
            Listing listing = Listing.builder()
                    .title(listingPostDTO.title())
                    .description(listingPostDTO.description())
                    .price(listingPostDTO.price())
                    .category(categoryService.find(listingPostDTO.category()))
                    .image(path.toString())
                    .user(User.builder().id(1).build())
                    .createdAt(LocalDateTime.now())
                    .build();

            listingService.create(listing);

            log.info("Listing successfully created");
            redirectAttributes.addFlashAttribute("message", "Listing created successfully");
            return new RedirectView("/listings/" + listing.getId());
        } catch (IOException e) {
            log.error("Error creating listing", e);
            redirectAttributes.addFlashAttribute("message", "Failed to upload image");
            return new RedirectView("/listings/create");
        }
    }
}
