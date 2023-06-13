package com.pragma.powerup.infrastructure.out.jpa.feign;


import com.pragma.powerup.application.dto.request.RestaurantEmployeeRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(contextId = "restaurantEmployee",name = "plazoleta-service", url = "localhost:8079/api/v1/restaurantEmployee")
public interface EmployeeFeignClient {

    @PostMapping("/")
    void saveRestaurantEmployee(RestaurantEmployeeRequestDto restaurantEmployeeRequestDto);
}
