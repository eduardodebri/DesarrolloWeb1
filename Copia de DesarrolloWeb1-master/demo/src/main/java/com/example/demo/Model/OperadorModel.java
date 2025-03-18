package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "operadores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperadorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nombre;

    @Column(unique = true)
    private String usuarioOp;
    @Column(nullable = false)
    private String contraseña;

    @OneToMany(mappedBy = "operador", cascade = CascadeType.ALL)
    private List<PedidoModel> pedidos;
}
