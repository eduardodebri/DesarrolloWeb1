package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "domiciliarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomiciliarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String celular;

    @Column(unique = true)
    private String cedula;
    @Column(nullable = false)
    private boolean disponibilidad;

    @OneToMany(mappedBy = "domiciliario", cascade = CascadeType.ALL)
    private List<PedidoModel> pedidos;

    public DomiciliarioModel(Integer id, String nombre, String celular, String cedula, boolean disponibilidad) {
        this.id = id;
        this.nombre = nombre;
        this.celular = celular;
        this.cedula = cedula;
        this.disponibilidad = disponibilidad;
    }
}
