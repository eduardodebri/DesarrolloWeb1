package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "carrito_compras")
@Data
@NoArgsConstructor
public class CarritoComprasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private float precioTotal;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteModel clienteModel;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
    private List<SeleccionarProductosModel> productosSeleccionados;

}
