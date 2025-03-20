package com.example.demo.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.demo.Model.AdicionalesModel;
import com.example.demo.Repository.AdicionalesRepository;
import jakarta.annotation.PostConstruct;

import com.example.demo.Model.ProductoModel;
import com.example.demo.Repository.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements ProductosServiceInterface {

    private final ProductoRepository productoRepository;
    private final AdicionalesRepository adicionalesRepository;

    public ProductoService(ProductoRepository productoRepository, AdicionalesRepository adicionalesRepository) {
        this.productoRepository = productoRepository;
        this.adicionalesRepository = adicionalesRepository;
    }

    public List<ProductoModel> obtenerProductos() {
        return productoRepository.findAll();
    }

    public void agregarProducto(ProductoModel producto) {
        productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    public ProductoModel obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public void agregarAdicionalAProducto(Long productoId, Long adicionalId) {
        Optional<ProductoModel> productoOpt = productoRepository.findById(productoId);
        Optional<AdicionalesModel> adicionalOpt = adicionalesRepository.findById(adicionalId);

        if (productoOpt.isPresent() && adicionalOpt.isPresent()) {
            ProductoModel producto = productoOpt.get();
            AdicionalesModel adicional = adicionalOpt.get();

            // Evitar duplicados en la lista de adicionales
            if (!producto.getAdicionales().contains(adicional)) {
                producto.getAdicionales().add(adicional);
                productoRepository.save(producto);
            }
        } else {
            throw new RuntimeException("Producto o Adicional no encontrado");
        }
    }



    public void removerAdicionalDeProducto(Long productoId, Long adicionalId) {
        Optional<ProductoModel> productoOpt = productoRepository.findById(productoId);
        Optional<AdicionalesModel> adicionalOpt = adicionalesRepository.findById(adicionalId);

        if (productoOpt.isPresent() && adicionalOpt.isPresent()) {
            ProductoModel producto = productoOpt.get();
            AdicionalesModel adicional = adicionalOpt.get();

            // Remover el adicional de la lista del producto
            producto.getAdicionales().remove(adicional);
            productoRepository.save(producto);
        } else {
            throw new RuntimeException("Producto o Adicional no encontrado");
        }
    }




        // Método para eliminar un adicional de todos los productos antes de eliminarlo
        public void eliminarAdicionalDeTodosLosProductos(Long adicionalId) {
            Optional<AdicionalesModel> adicionalOpt = adicionalesRepository.findById(adicionalId);
            if (adicionalOpt.isPresent()) {
                AdicionalesModel adicional = adicionalOpt.get();

                // Obtener todos los productos
                List<ProductoModel> productos = productoRepository.findAll();

                for (ProductoModel producto : productos) {
                    if (producto.getAdicionales().contains(adicional)) {
                        producto.getAdicionales().remove(adicional);
                        productoRepository.save(producto); // Guardar cambios en el producto
                    }
                }
            }
        }

          


    }






