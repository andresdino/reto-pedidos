package com.pragma.powerup.application.mapper.request;


import com.pragma.powerup.application.dto.request.RolRequestDTO;
import com.pragma.powerup.domain.model.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRolRequestMapper {

    Rol toRol(RolRequestDTO rolRequestDTO);
}
