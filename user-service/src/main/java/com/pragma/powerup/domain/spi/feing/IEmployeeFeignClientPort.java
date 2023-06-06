package com.pragma.powerup.domain.spi.feing;

import com.pragma.powerup.domain.model.RestaurantEmployeeModel;

public interface IEmployeeFeignClientPort {

    void saveRestaurantEmployee(RestaurantEmployeeModel restaurantEmployeeModel);

}
