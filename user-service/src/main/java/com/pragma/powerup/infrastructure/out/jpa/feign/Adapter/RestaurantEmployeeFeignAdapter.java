package com.pragma.powerup.infrastructure.out.jpa.feign.Adapter;

import com.pragma.powerup.application.dto.request.RestaurantEmployeeRequestDto;
import com.pragma.powerup.application.mapper.request.IRestaurantEmployeeRequestMapper;
import com.pragma.powerup.domain.model.RestaurantEmployeeModel;
import com.pragma.powerup.domain.spi.feing.IEmployeeFeignClientPort;
import com.pragma.powerup.infrastructure.out.jpa.feign.EmployeeFeignClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantEmployeeFeignAdapter implements IEmployeeFeignClientPort {

    private  final EmployeeFeignClient employeeFeignClient;

    private final IRestaurantEmployeeRequestMapper restaurantEmployeeRequestMapper;

    @Override
    public void saveRestaurantEmployee(RestaurantEmployeeModel restaurantEmployeeModel) {
        RestaurantEmployeeRequestDto restaurantEmployeeRequestDto = restaurantEmployeeRequestMapper.toRequest(restaurantEmployeeModel);
        employeeFeignClient.saveRestaurantEmployee(restaurantEmployeeRequestDto);
    }


}