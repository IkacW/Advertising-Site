package com.SpringWebProject.Advertising.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Category {
    @Id
    @GeneratedValue
    private int id;

    // Just uncomment when you manually add distinct categories.
    @Column(nullable = false/*, unique = true*/)
    private String name;

    @OneToMany(
            mappedBy = "category"
    )
    private List<Listing> listings;
}
