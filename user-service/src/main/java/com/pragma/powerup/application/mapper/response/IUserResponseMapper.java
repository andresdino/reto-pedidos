package com.pragma.powerup.application.mapper.response;


import com.pragma.powerup.application.dto.response.UserResponseDTO;
import com.pragma.powerup.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {

    UserResponseDTO toResponse(User user);

    List<UserResponseDTO> toResponseList(List<User> userModelList);


}
