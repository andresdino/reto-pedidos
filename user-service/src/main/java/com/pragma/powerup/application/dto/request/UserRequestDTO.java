package com.pragma.powerup.application.dto.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRequestDTO {

    @NotBlank(message = "El campo nombre es requerido")
    private String nombre;

    @NotBlank(message = "El campo apellido es requerido")
    private String apellido;

    @NotBlank(message = "El campo documento de identidad es requerido")
    @Pattern(regexp = "\\d+", message = "El documento de identidad debe ser únicamente numérico")
    private String documentoDeIdentidad;

    @NotBlank(message = "El campo celular es requerido")
    @Pattern(regexp = "^\\+?\\d{1,12}$", message = "el teléfono debe contener un máximo de 13 caracteres y puede contener el símbolo +. Ejemplo: +573005698325")
    private String celular;

    @NotBlank(message = "El campo correo es requerido")
    @Email(message = "Se debe verificar estructura de email válida")
    private String correo;

    @NotBlank(message = "El campo clave es requerido")
    private String clave;
    private Long rol;
}
