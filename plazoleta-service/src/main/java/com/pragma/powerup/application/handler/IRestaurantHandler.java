package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.RestaurantRequestDTO;
import com.pragma.powerup.application.dto.response.RestaurantResponseDTO;

import java.util.List;

public interface IRestaurantHandler {

    void saveRestaurant(RestaurantRequestDTO restaurantRequestDTO);

    RestaurantResponseDTO getRestaurantById(Long id);

    List<RestaurantResponseDTO> getAllRestaurant();
}
