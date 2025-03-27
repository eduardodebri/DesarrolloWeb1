package com.example.demo.Service;

import com.example.demo.Model.ProductoModel;
import java.util.List;

public interface ProductosServiceInterface {

    List<ProductoModel> obtenerProductos(); // Obtener todos los productos

    ProductoModel obtenerProductoPorId(Long id); // Obtener un producto por su ID

    void agregarProducto(ProductoModel producto); // Guardar o actualizar un producto

    void eliminarProducto(Long id); // Eliminar un producto por ID

    void agregarAdicionalAProducto(Long productoId, Long adicionalId); // Agregar un adicional a un producto

    void removerAdicionalDeProducto(Long productoId, Long adicionalId); // Remover un adicional de un producto

    void eliminarAdicionalDeTodosLosProductos(Long adicionalId);
}
