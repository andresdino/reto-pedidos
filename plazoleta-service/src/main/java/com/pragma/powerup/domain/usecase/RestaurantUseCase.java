package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.spi.persistence.IRestaurantPersistencePort;

import java.util.List;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {

        restaurantPersistencePort.saveRestaurant(restaurant);

    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return restaurantPersistencePort.getRestaurantById(id);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantPersistencePort.getAllRestaurant();
    }
}
