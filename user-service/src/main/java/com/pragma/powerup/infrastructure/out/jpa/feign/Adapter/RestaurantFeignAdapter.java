package com.pragma.powerup.infrastructure.out.jpa.feign.Adapter;

import com.pragma.powerup.application.dto.response.RestaurantResponseDTO;
import com.pragma.powerup.application.mapper.response.IRestaurantResponseMapper;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.feing.IClientFeignPort;
import com.pragma.powerup.infrastructure.out.jpa.feign.ClientFeign;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantFeignAdapter implements IClientFeignPort {

    private final ClientFeign clientFeign;

    private final IRestaurantResponseMapper restaurantResponseMapper;
    @Override
    public RestaurantModel getRestaurantByIdPropietario(Long idPropietario) {
        RestaurantResponseDTO restaurantResponseDto = clientFeign.getRestaurantByIdPropietario(idPropietario);
        return restaurantResponseMapper.toRestaurantModel(restaurantResponseDto);
    }
}
