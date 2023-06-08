package com.pragma.powerup.domain.model;

import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEmployeeEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orden {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "pedido_id", nullable = false)
    private Long id;

    @Column(name = "cliente_id", nullable = false)
    private Long idCliente;
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    @Column(name = "estado")
    private String estado;
    @ManyToOne
    @JoinColumn(name = "chef_id")
    private RestaurantEmployee chef;
    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurant restaurante;
}
