package com.pragma.powerup.infrastructure.out.jpa.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="nombre", length = 220)
    private String nombre;

    @Column(name="apellido", length = 220)
    private String apellido;

    @Column(name="documento_de_identidad",  unique = true)
    private String documentoDeIdentidad;

    @Column(name="celular", length = 13)
    private String celular;

    @Column(name="correo", unique = true)
    private String correo;

    @Column(name="clave")
    private String clave;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "rol", nullable = false)
    private RolEntity rol;


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", documentoIdentidad=" + documentoDeIdentidad +
                ", celular='" + celular + '\'' +
                ", email='" + correo + '\'' +
                ", clave='" + clave + '\'' +
                ", rol=" + rol +
                '}';
    }
}
