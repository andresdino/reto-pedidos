package com.pragma.powerup.domain.model.Ordenes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdenResponse {
    private Long id;
    private Long idCliente;
    private Long idChef;
    private Date fecha;
    private List<OrdenPlatoResponse> pedidoPlatos;
}
