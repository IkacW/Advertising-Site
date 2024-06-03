package com.SpringWebProject.Advertising.Services;

import com.SpringWebProject.Advertising.Mappers.ListingMapper;
import com.SpringWebProject.Advertising.Models.DTOs.ListingPostDTO;
import com.SpringWebProject.Advertising.Models.DTOs.PaginatedResponseDTO;
import com.SpringWebProject.Advertising.Models.Listing;
import com.SpringWebProject.Advertising.Repositories.ListingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public PaginatedResponseDTO<ListingPostDTO> getAllListings(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Listing> page = listingRepository.findAll(pageable);
        List<ListingPostDTO> content = page.getContent()
                .stream()
                .map(listingMapper::toListingPostDTO)
                .toList();

        return new PaginatedResponseDTO<>(
                content,
                pageNo,
                pageSize,
                page.getTotalPages(),
                page.getTotalElements()
        );
    }
}
