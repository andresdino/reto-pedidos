package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;



@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RolEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "rol_id",nullable = false)
    private Long id;
    //@NotNull(message = "El nombre del rol no puede ser nulo")

    @Column(name = "nombre")
    @NotBlank
    private  String nombre;
    // @NotNull(message = "La descripcion no puede ser nula")
    @Column(name = "descripcion")
    @NotBlank
    private String descripcion;


}
