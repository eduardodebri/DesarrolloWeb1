package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String estado;
    @Column(nullable = false)
    private Date fechaCreacion;
    @Column(nullable = false)
    private Date fechaEntrega;

    @ManyToOne
    @JoinColumn(name = "operador_id")
    private OperadorModel operador;

    @ManyToOne
    @JoinColumn(name = "domiciliario_id")
    private DomiciliarioModel domiciliario;
}
