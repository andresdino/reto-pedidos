package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.exception.OwnerMustOnlyOwnARestaurantException;
import com.pragma.powerup.domain.exception.UserMustBeOwnerException;
import com.pragma.powerup.domain.exception.UserNotExistException;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.feignClients.IUserFeignClientPort;
import com.pragma.powerup.domain.spi.persistence.IRestaurantPersistencePort;

import java.util.List;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;

    private final IUserFeignClientPort userFeignClient;


    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort ,IUserFeignClientPort userFeignClient) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userFeignClient =  userFeignClient;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        boolean existUser = userFeignClient.existsUserById(restaurant.getIdPropietario());
        if (!existUser) throw new UserNotExistException();
        User user = userFeignClient.getUserById(restaurant.getIdPropietario());
        if (user.getRol().getId() != 2) throw new UserMustBeOwnerException();
        Restaurant restaurantModel2 =restaurantPersistencePort.getRestaurantByIdPropietario(user.getId());
        if(restaurantModel2 != null) throw new OwnerMustOnlyOwnARestaurantException();
        restaurantPersistencePort.saveRestaurant(restaurant);
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return restaurantPersistencePort.getRestaurantById(id);
    }

    @Override
    public Restaurant getRestaurantByIdPropietario(Long idpropietario) {
        return restaurantPersistencePort.getRestaurantByIdPropietario(idpropietario);
    }
    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantPersistencePort.getAllRestaurants();
    }

    @Override
    public void deleteRestaurantById(Long id) {
        restaurantPersistencePort.deleteRestaurantById(id);
    }

    @Override
    public List<Restaurant> getRestaurantsWithPagination(Integer page, Integer size) {
        return restaurantPersistencePort.getRestaurantsWithPagination(page,size);
    }

}
