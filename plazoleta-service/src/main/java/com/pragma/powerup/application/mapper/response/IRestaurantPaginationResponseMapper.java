package com.pragma.powerup.application.mapper.response;

import com.pragma.powerup.application.dto.response.RestaurantPaginationResponseDTO;
import com.pragma.powerup.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantPaginationResponseMapper {

    List<RestaurantPaginationResponseDTO> toResponseListPagination(List<Restaurant> restaurantModelList);

}