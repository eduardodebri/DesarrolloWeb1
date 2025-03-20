package com.example.demo.Model;



import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import com.example.demo.Model.AdicionalesModel;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
public class ProductoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private double precio;
    @Column(nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private String imagenUrl;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "producto_adicional",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "adicional_id")
    )
    private List<AdicionalesModel> adicionales;

    public ProductoModel( String nombre, double precio, String descripcion, String imagenUrl) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.imagenUrl = imagenUrl;
        this.adicionales = new ArrayList<>();
    }


}
