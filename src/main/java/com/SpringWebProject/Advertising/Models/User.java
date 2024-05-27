package com.SpringWebProject.Advertising.Models;

import com.SpringWebProject.Advertising.Models.Embedded.Address;
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
@Table(name = "_user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String phone;

    @Embedded
    private Address address;

    // Relationships
    @OneToMany(
            mappedBy = "user"
    )
    private List<Listing> listings;

    @ManyToMany(
            mappedBy = "users_following"
    )
    private List<Listing> following_listings;
}
