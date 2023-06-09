package com.pragma.powerup.application.mapper.response;


import com.pragma.powerup.application.dto.response.RestaurantEmployeeResponseDto;
import com.pragma.powerup.application.dto.response.RestaurantResponseDTO;
import com.pragma.powerup.domain.model.RestaurantEmployeeModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantResponseMapper {

    RestaurantResponseDTO toResponse(RestaurantModel restaurantModel);


    RestaurantModel toRestaurantModel(RestaurantResponseDTO restaurantResponseDto);

    //List<RestaurantEmployeeResponseDto> toResponseList(List<RestaurantEmployeeModel> restaurantEmployeeModelList);
}
