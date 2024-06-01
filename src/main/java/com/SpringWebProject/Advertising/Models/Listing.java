package com.SpringWebProject.Advertising.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Listing extends BaseEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(
            nullable = false,
            length = 1000
    )
    private String description;

    private String image;

    @Column(nullable = false)
    private float price;

    private int discount;

    // Relationships
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;

    @ManyToMany
    @JoinTable(
            name = "wishlist",
            joinColumns = {
                    @JoinColumn(name = "listing_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id")
            }
    )
    private List<User> users_following;

    @ManyToOne
    @JoinColumn(
            name = "category_id"
    )
    private Category category;
}
