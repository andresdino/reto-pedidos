package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.RestauranteRequestDTO;
import com.pragma.powerup.application.handler.IRestauranteHandler;
import com.pragma.powerup.application.mapper.request.IRestauranteRequestMapper;
import com.pragma.powerup.application.mapper.response.IRestauranteResponseMapper;
import com.pragma.powerup.domain.api.IRestauranteServicePort;
import com.pragma.powerup.domain.model.RestauranteModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class ImplRestauranteHandler implements IRestauranteHandler {

    private final IRestauranteServicePort restauranteServicePort;
    private final IRestauranteRequestMapper restauranteRequestMapper;
    private final IRestauranteResponseMapper restauranteResponseMapper;

    @Override
    public void saveRestaurante(RestauranteRequestDTO restauranteRequestDTO) {
        RestauranteModel restauranteModel = restauranteRequestMapper.toRestaurante(restauranteRequestDTO)
        restauranteServicePort.saveRestaurant(restauranteModel);
    }
}
