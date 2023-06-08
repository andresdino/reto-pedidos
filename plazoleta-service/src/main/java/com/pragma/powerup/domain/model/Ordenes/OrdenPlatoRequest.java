package com.pragma.powerup.domain.model.Ordenes;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdenPlatoRequest {

    private Long idPlatos;
    private Long cantidad;
}
