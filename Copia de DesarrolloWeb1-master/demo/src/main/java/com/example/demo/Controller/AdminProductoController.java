package com.example.demo.Controller;

import com.example.demo.Model.AdicionalesModel;
import com.example.demo.Repository.AdicionalesRepository;
import com.example.demo.Service.AdicionalesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Model.ProductoModel;
import com.example.demo.Service.ProductoService;


import java.util.List;

@Controller
@RequestMapping("/admin/productos")
public class AdminProductoController {

    private final ProductoService productoService;
    private final AdicionalesService adicionalesService;

    public AdminProductoController(ProductoService productoService, AdicionalesService adicionalesService) {
            this.productoService = productoService;
        this.adicionalesService = adicionalesService;
    }

    @GetMapping("/")
    public String listarProductos(Model model) {
        List<ProductoModel> productos = productoService.obtenerProductos();
        model.addAttribute("productos", productos);
        return "adminProductos1"; // Renderiza productos.html
    }

    @GetMapping("/editar/{id}")
    public String mostrarProducto(@PathVariable Long id, Model model) {
        ProductoModel producto = productoService.obtenerProductoPorId(id);
        List<AdicionalesModel> adicionales = adicionalesService.obtenerAdicionales(); // Obtiene todos los adicionales disponibles

        model.addAttribute("producto", producto);  // Envía el producto seleccionado
        model.addAttribute("adicionales", adicionales); // Envía la lista de adicionales

        return "editarProducto";
    }


    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute ProductoModel producto) {
        productoService.agregarProducto(producto); // Guarda o actualiza el producto
        return "redirect:/admin/productos/";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/admin/productos/";
    }


    @PostMapping("/{productoId}/agregar-adicional")
    public String agregarAdicional(@PathVariable Long productoId, @RequestParam Long adicionalId) {
        productoService.agregarAdicionalAProducto(productoId, adicionalId);
        return "redirect:/admin/productos/editar/" + productoId; // Redirige a la edición del producto
    }


    @GetMapping("/{productoId}/remover-adicional/{adicionalId}")
    public String removerAdicional(@PathVariable Long productoId, @PathVariable Long adicionalId) {
        productoService.removerAdicionalDeProducto(productoId, adicionalId);
        return "redirect:/admin/productos/editar/" + productoId;
    }

    @GetMapping("/agregar")
    public String agregarProducto(Model model) {
        model.addAttribute("producto", new ProductoModel());
        return "AgregarProducto"; // Redirige a la lista de productos después de agregar
    }


}
