package com.SpringWebProject.Advertising.Services;

import com.SpringWebProject.Advertising.Mappers.ListingMapper;
import com.SpringWebProject.Advertising.Models.DTOs.ListingPostDTO;
import com.SpringWebProject.Advertising.Repositories.ListingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingService {
    private final ListingRepository listingRepository;
    private final ListingMapper listingMapper;

    public ListingService(ListingRepository listingRepository, ListingMapper listingMapper) {
        this.listingRepository = listingRepository;
        this.listingMapper = listingMapper;
    }

    public List<ListingPostDTO> getAllListings(){
        return listingRepository.findAll()
                .stream()
                .map(listingMapper::toListingPostDTO)
                .toList();
    }
}
