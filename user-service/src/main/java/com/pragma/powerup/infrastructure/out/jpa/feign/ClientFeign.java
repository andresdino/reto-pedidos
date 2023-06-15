package com.pragma.powerup.infrastructure.out.jpa.feign;


import com.pragma.powerup.application.dto.response.RestaurantResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "restaurant",name = "plazoleta-service", url = "localhost:8077/api/v1/restaurant")
public interface ClientFeign {


    @GetMapping("/restaurantByIdPropietario/{id}")
    RestaurantResponseDTO getRestaurantByIdPropietario(@PathVariable(value = "id") Long idPropietario);
}
