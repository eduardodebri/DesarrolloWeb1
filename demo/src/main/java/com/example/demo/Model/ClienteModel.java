package com.example.demo.Model;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String direccion;

    public ClienteModel() {}

    public ClienteModel(String nombre, String apellido, String email, String contraseña, String telefono, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.direccion = direccion;
    }


}
