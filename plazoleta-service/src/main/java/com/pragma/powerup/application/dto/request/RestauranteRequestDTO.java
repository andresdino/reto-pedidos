package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
public class RestauranteRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Pattern(regexp = "\\d+", message = "El NIT debe contener solo números")
    @NotBlank(message = "El NIT es obligatorio")
    private String nit;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @Pattern(regexp = "\\d+", message = "El teléfono del restaurante debe contener solo números")
    @Size(max = 13, message = "El teléfono del restaurante debe tener como máximo 13 caracteres")
    @NotBlank(message = "El teléfono del restaurante es obligatorio")
    private String telefonoRestaurante;

    @NotBlank(message = "La URL del logo es obligatoria")
    private String urlLogo;

    @NotNull(message = "El ID del usuario propietario es obligatorio")
    private Long idUsuarioPropietario;
}
