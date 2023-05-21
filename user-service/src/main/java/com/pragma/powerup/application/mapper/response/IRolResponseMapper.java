package com.pragma.powerup.application.mapper.response;

import com.pragma.powerup.application.dto.response.RolResponseDTO;
import com.pragma.powerup.domain.model.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRolResponseMapper {

    RolResponseDTO toResponse(Rol rol);

    List<RolResponseDTO> toResponsList(List<Rol> roleList);


}
