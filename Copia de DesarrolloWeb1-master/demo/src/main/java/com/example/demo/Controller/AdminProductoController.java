package com.example.demo.Controller;

import com.example.demo.Model.AdicionalesModel;
import com.example.demo.Model.ProductoModel;
import com.example.demo.Service.AdicionalesService;
import com.example.demo.Service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    // LISTAR TODOS LOS PRODUCTOS
    @GetMapping("/")
    public String listarProductos(Model model) {
        List<ProductoModel> productos = productoService.obtenerProductos();
        model.addAttribute("productos", productos);
        return "adminProductos1"; 
    }

    // MOSTRAR FORMULARIO PARA AGREGAR UN PRODUCTO
    @GetMapping("/agregar")
    public String agregarProducto(Model model) {
        model.addAttribute("producto", new ProductoModel());
        List<AdicionalesModel> adicionales = adicionalesService.obtenerAdicionales();
        model.addAttribute("adicionales", adicionales);
        return "agregarProducto";
    }

    // GUARDAR UN NUEVO PRODUCTO
    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute ProductoModel producto, @RequestParam(required = false) List<Long> adicionalesSeleccionados) {
        if (adicionalesSeleccionados != null && !adicionalesSeleccionados.isEmpty()) {
            List<AdicionalesModel> adicionales = adicionalesService.obtenerAdicionales();
            producto.setAdicionales(adicionales);
        }
        productoService.agregarProducto(producto);
        return "redirect:/admin/productos/";
    }

    // MOSTRAR FORMULARIO PARA EDITAR UN PRODUCTO
    @GetMapping("/editar/{id}")
    public String mostrarProducto(@PathVariable Long id, Model model) {
        ProductoModel producto = productoService.obtenerProductoPorId(id);
        List<AdicionalesModel> adicionales = adicionalesService.obtenerAdicionales();

        model.addAttribute("producto", producto);
        model.addAttribute("adicionales", adicionales);

        return "editarProducto";
    }

    // ELIMINAR UN PRODUCTO
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/admin/productos/";
    }

    // AGREGAR UN ADICIONAL A UN PRODUCTO
    @PostMapping("/{productoId}/agregar-adicional")
    public String agregarAdicional(@PathVariable Long productoId, @RequestParam Long adicionalId) {
        productoService.agregarAdicionalAProducto(productoId, adicionalId);
        return "redirect:/admin/productos/editar/" + productoId;
    }

    // REMOVER UN ADICIONAL DE UN PRODUCTO
    @GetMapping("/{productoId}/remover-adicional/{adicionalId}")
    public String removerAdicional(@PathVariable Long productoId, @PathVariable Long adicionalId) {
        productoService.removerAdicionalDeProducto(productoId, adicionalId);
        return "redirect:/admin/productos/editar/" + productoId;
    }
}
