package com.pragma.powerup.infrastructure.out.jpa.feignClients.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String documentoDeIdentidad;
    private String celular;
    private String correo;
    private String clave;
    private RolDTO rol;

}
