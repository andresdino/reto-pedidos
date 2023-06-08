package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrdenResponseDTO {
    private Long id;
    private Long idCliente;
    private Long idChef;
    private Date fecha;
    private List<OrderDishResponseDTO> pedidoPlatos;
}
