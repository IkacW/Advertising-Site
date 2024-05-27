package com.SpringWebProject.Advertising.Repositories;

import com.SpringWebProject.Advertising.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
