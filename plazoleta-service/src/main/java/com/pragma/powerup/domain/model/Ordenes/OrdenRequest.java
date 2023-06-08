package com.pragma.powerup.domain.model.Ordenes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdenRequest {
    private List<OrdenPlatoRequest> platos;
    private Long  resturanteId;
}
