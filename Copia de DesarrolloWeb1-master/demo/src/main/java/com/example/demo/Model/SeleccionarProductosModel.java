package com.example.demo.Model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "seleccionar_productos")
@Data
public class SeleccionarProductosModel {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Column(nullable = false)
        private int cantidad;

        @ManyToOne
        @JoinColumn(name = "carrito_id")
        private CarritoComprasModel carrito;

        @ManyToOne
        @JoinColumn(name = "producto_id")
        private ProductoModel producto;


}
