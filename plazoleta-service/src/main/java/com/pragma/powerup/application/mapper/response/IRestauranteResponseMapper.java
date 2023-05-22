package com.pragma.powerup.application.mapper.response;


import com.pragma.powerup.application.dto.response.RestauranteResponseDTO;
import com.pragma.powerup.domain.model.RestauranteModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestauranteResponseMapper {

    RestauranteResponseDTO toResponse(RestauranteModel restauranteModel);
}
