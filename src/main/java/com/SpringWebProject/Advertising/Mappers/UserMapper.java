package com.SpringWebProject.Advertising.Mappers;

import com.SpringWebProject.Advertising.Models.DTOs.UserAuthorDTO;
import com.SpringWebProject.Advertising.Models.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserAuthorDTO toUserAuthorDTO(User user) {
        return new UserAuthorDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getUsername()
        );
    }
}
