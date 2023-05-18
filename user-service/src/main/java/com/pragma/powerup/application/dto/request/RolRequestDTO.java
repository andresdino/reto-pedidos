package com.pragma.powerup.application.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RolRequestDTO {

    @NotBlank(message = "El campo nombre es requerido")
    private String nombre;

    @NotBlank(message = "El campo descripcion es requerido")
    private  String descripcion;

}
