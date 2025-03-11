package com.example.demo.Controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.ProductoModel;
import com.example.demo.Service.ProductoService;


@Controller
@RequestMapping("/hanami/productos")
public class ProductoController {

    private final ProductoService productoService;

        public ProductoController(ProductoService productoService) {
            this.productoService = productoService;
        }

        
        @GetMapping("/")
        public String listarProductos(Model model) {
            List<ProductoModel> productos = productoService.obtenerProductos();
            model.addAttribute("productos", productos);
            return "menuProductos"; // Renderiza productos.html
        }

        @PostMapping("/agregar")
        public String agregarProducto(@ModelAttribute ProductoModel producto) {
            productoService.agregarProducto(producto);
            return "redirect:/productos/";
        }

        @GetMapping("/eliminar/{id}")
        public String eliminarProducto(@PathVariable Long id) {
            productoService.eliminarProducto(id);
            return "redirect:/productos/";
        }
    @GetMapping("/{id}")
    public String mostrarProducto(@PathVariable Long id, Model model) {
        ProductoModel producto = productoService.obtenerProductoPorId(id);
        model.addAttribute("producto", producto);
        return "descripcion_producto"; // Retorna la vista detalleProducto.html
    }
    
}
