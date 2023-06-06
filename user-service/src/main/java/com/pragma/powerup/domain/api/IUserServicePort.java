package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IUserServicePort {

    void saveUser(User user);

    User getUserById(Long id);

   List<User> getAllUser();


    void saveRestaurantEmployee(User user);
}
