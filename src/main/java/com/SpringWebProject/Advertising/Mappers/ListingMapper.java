package com.SpringWebProject.Advertising.Mappers;

import com.SpringWebProject.Advertising.Models.DTOs.ListingDTO;
import com.SpringWebProject.Advertising.Models.DTOs.UserAuthorDTO;
import com.SpringWebProject.Advertising.Models.Listing;
import org.springframework.stereotype.Service;

@Service
public class ListingMapper {

    public ListingDTO toListingPostDTO(
            Listing listing,
            UserAuthorDTO userAuthorDTO
    ) {
        if(listing == null) {
            return null;
        }
        return new ListingDTO(
                listing.getId(),
                listing.getTitle(),
                listing.getDescription(),
                listing.getPrice(),
                listing.getDiscount(),
                userAuthorDTO
        );
    }

//    public Listing toListing(ListingPostDTO listingPostDTO) {
//        if (listingPostDTO == null) {
//            throw new NullPointerException("Listing Post DTO is Null");
//        }
//
//        var listing = new Listing();
//
//        listing.set
//    }
}
