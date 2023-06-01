package com.pragma.powerup.application.handler.impl;


import com.pragma.powerup.application.dto.request.RestaurantRequestDTO;
import com.pragma.powerup.application.dto.response.RestaurantResponseDTO;
import com.pragma.powerup.application.handler.IRestaurantHandler;
import com.pragma.powerup.application.mapper.request.IRestaurantRequestMapper;
import com.pragma.powerup.application.mapper.response.IRestaurantResponseMapper;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantResponseMapper restaurantResponseMapper;
    private final IRestaurantRequestMapper restaurantRequestMapper;


    @Override
    public void saveRestaurant(RestaurantRequestDTO restaurantRequestDTO) {
        Restaurant restaurant = restaurantRequestMapper.toRestaurant(restaurantRequestDTO);
        restaurantServicePort.saveRestaurant(restaurant);
    }

    @Override
    public RestaurantResponseDTO getRestaurantById(Long id) {
        RestaurantResponseDTO restaurantResponseDTO = restaurantResponseMapper.toResponse(restaurantServicePort.getRestaurantById(id));
        return restaurantResponseDTO;
    }

    @Override
    public List<RestaurantResponseDTO> getAllRestaurant() {
        return restaurantResponseMapper.toResponseList(restaurantServicePort.getAllRestaurant());
    }
}
