package com.example.demo.Controller;

import com.example.demo.Model.ClienteModel;
import com.example.demo.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private  ClienteService clienteService;


    // 🔹 Muestra el formulario de registro (Corregí el nombre de la vista)
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("cliente", new ClienteModel());
        return "logon"; // Asegúrate de que el archivo se llama "logOn.html"
    }

    // 🔹 Procesa el formulario de registro
    @PostMapping("/registro")
    public String registrarCliente(@ModelAttribute ClienteModel cliente) {
        clienteService.guardarCliente(cliente);

        // Evitar `NullPointerException` al redirigir antes de que el ID esté generado
        return "redirect:/cliente/perfil/" + cliente.getId();
    }

    // 🔹 Muestra el formulario de login
    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login"; // Asegúrate de que el archivo se llama "login.html"
    }

    // 🔹 Procesa el login
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String email,
                                @RequestParam String contrasena,
                                Model model) {
        ClienteModel cliente = clienteService.buscarPorEmail(email);

        if (cliente != null && cliente.getContrasena().equals(contrasena)) {
            model.addAttribute("cliente", cliente);
            return "perfilCliente"; // Redirige al perfil si las credenciales son correctas
        } else {
            model.addAttribute("error", "Email o contraseña incorrectos.");
            return "login"; // Vuelve al login si falla
        }
    }




    // 🔹 Ver perfil del cliente
    @GetMapping("/perfil/{id}")
    public String verPerfil(@PathVariable Long id, Model model) {
        ClienteModel cliente = clienteService.obtenerClientePorId(id);
        if (cliente != null) {
            model.addAttribute("cliente", cliente);
            return "perfilCliente"; // Asegúrate de que el archivo se llama "perfilCliente.html"
        }
        return "redirect:/error"; // Si no se encuentra el cliente
    }

    // 🔹 Editar perfil del cliente (mostrar formulario)
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        ClienteModel cliente = clienteService.obtenerClientePorId(id);
        if (cliente != null) {
            model.addAttribute("cliente", cliente);
            return "editarCliente"; // Asegúrate de que el archivo se llama "editarCliente.html"
        }
        return "redirect:/error"; // Si no se encuentra el cliente
    }

    // 🔹 Guardar cambios en perfil del cliente
    @PostMapping("/editar")
    public String actualizarPerfil(@ModelAttribute ClienteModel cliente) {
        clienteService.guardarCliente(cliente);
        return "redirect:/cliente/perfil/" + cliente.getId();
    }

    // 🔹 Eliminar cuenta del cliente
    @GetMapping("/eliminar/{id}")
    public String eliminarCuenta(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return "redirect:/"; // Redirige a la página de inicio tras eliminar la cuenta
    }
}
