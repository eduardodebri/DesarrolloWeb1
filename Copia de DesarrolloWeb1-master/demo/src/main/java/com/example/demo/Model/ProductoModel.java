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
    private int id;

    private String nombre;
    private double precio;
    private String descripcion;
    private String imagenUrl;
    @ManyToMany
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public List<AdicionalesModel> getAdicionales() {
        return adicionales;
    }

    public void setAdicionales(List<AdicionalesModel> adicionales) {
        this.adicionales = adicionales;
    }
}
