package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.RestaurantEmployeeRequestDto;
import com.pragma.powerup.application.dto.response.RestaurantEmployeeResponseDto;
import com.pragma.powerup.application.handler.IRestaurantEmployeeHandler;
import com.pragma.powerup.application.mapper.request.IRestaurantEmployeeRequestMapper;
import com.pragma.powerup.application.mapper.response.IRestaurantEmployeeResponseMapper;
import com.pragma.powerup.domain.api.IRestaurantEmployeeServicePort;
import com.pragma.powerup.domain.model.RestaurantEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantEmployeeHandler implements IRestaurantEmployeeHandler {


    private final IRestaurantEmployeeServicePort restaurantEmployeeServicePort;

    private final IRestaurantEmployeeRequestMapper restaurantEmployeeRequestMapper;

    private final IRestaurantEmployeeResponseMapper restaurantEmployeeResponseMapper;

    @Override
    public void saveRestaurantEmployee(RestaurantEmployeeRequestDto restaurantEmployeeRequestDto) {
        RestaurantEmployee restaurantEmployeeModel = restaurantEmployeeRequestMapper.toRestaurantEmployee(restaurantEmployeeRequestDto);
        restaurantEmployeeServicePort.saveRestaurantEmployee(restaurantEmployeeModel);

    }

    @Override
    public List<RestaurantEmployeeResponseDto> getAllRestaurantEmployees() {
        return restaurantEmployeeResponseMapper.toResponseList(restaurantEmployeeServicePort.getAllRestaurantEmployees());
    }
}