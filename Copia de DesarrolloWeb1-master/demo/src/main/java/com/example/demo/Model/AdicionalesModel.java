package com.example.demo.Model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import com.example.demo.Model.ProductoModel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "adicionales")
@Data
@NoArgsConstructor
public class AdicionalesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private double precio;

    @ManyToMany(mappedBy = "adicionales")
    private List<ProductoModel> productos;

    public AdicionalesModel( String nombre, double precio) {

        this.nombre = nombre;
        this.precio = precio;
        this.productos = new ArrayList<>();
    }


}
