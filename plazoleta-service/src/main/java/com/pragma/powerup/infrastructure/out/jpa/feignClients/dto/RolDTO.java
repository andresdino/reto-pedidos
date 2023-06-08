package com.pragma.powerup.infrastructure.out.jpa.feignClients.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolDTO {

    private Long id;
    private String nombre;
    private String descripcion;
}
