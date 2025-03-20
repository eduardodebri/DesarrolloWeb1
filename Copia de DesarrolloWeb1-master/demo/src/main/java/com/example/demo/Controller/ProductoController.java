package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.AdicionalesModel;
import com.example.demo.Model.ProductoModel;
import com.example.demo.Service.AdicionalesService;
import com.example.demo.Service.ProductoService;

@Controller
@RequestMapping("/hanami/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private AdicionalesService adicionalesService; // Se inyecta correctamente

    @GetMapping("/")
    public String listarProductos(Model model) {
        List<ProductoModel> productos = productoService.obtenerProductos();
        model.addAttribute("productos", productos);
        return "menuProductos"; // Renderiza la lista de productos
    }

    @PostMapping("/agregar")
    public String agregarProducto(@ModelAttribute ProductoModel producto, 
                                  @RequestParam(required = false) List<Long> adicionalesIds) {
        if (adicionalesIds != null && !adicionalesIds.isEmpty()) {
            List<AdicionalesModel> adicionalesSeleccionados = adicionalesService.obtenerAdicionalesPorIds(adicionalesIds);
            producto.setAdicionales(adicionalesSeleccionados);
        } else {
            producto.setAdicionales(null); 
        }
        productoService.agregarProducto(producto);
        
        return "redirect:/admin/productos/";    
    }
    

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new ProductoModel());
        model.addAttribute("adicionales", adicionalesService.obtenerAdicionales()); // Se obtienen todos los adicionales
        return "agregarProducto"; 
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/hanami/productos/";
    }

    @GetMapping("/{id}")
    public String mostrarProducto(@PathVariable Long id, Model model) {
        ProductoModel producto = productoService.obtenerProductoPorId(id);
        model.addAttribute("producto", producto);
        return "descripcion_producto"; 
    }
}
