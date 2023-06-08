package com.pragma.powerup.domain.spi.persistence;

import com.pragma.powerup.domain.model.RestaurantEmployee;

import java.util.List;
import java.util.Optional;

public interface IRestaurantEmployeePersistencePort {
    RestaurantEmployee saveRestaurantEmployee(RestaurantEmployee restaurantEmployee);

    List<RestaurantEmployee> getAllRestaurantEmployees();

    RestaurantEmployee findByPersonId(String idEmpleado);
}