package com.pragma.powerup.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdenPlato {

    private Long id;
    private Orden pedido;
    private Plato plato;
    private String cantidad;

}
