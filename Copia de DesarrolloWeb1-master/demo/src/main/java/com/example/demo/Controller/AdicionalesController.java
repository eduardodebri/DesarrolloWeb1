package com.example.demo.Controller;

import com.example.demo.Model.AdicionalesModel;
import com.example.demo.Model.ClienteModel;
import com.example.demo.Repository.AdicionalesRepository;
import com.example.demo.Service.AdicionalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/adicionales")
public class AdicionalesController {
    @Autowired
    private  AdicionalesService adicionalService;
    @Autowired
    private  AdicionalesService adicionalesService;



    // Listar todos los adicionales
    @GetMapping("/")
    public String listarAdicionales(Model model) {
        List<AdicionalesModel> adicionales = adicionalService.obtenerAdicionales();
        model.addAttribute("adicionales", adicionales);
        return "listaAdicionales";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("adicional", new AdicionalesModel());
        return "formularioAdicional";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Optional<AdicionalesModel> adicionalesModel = adicionalesService.obtenerAdicionalPorId(id);
        if (adicionalesModel != null) {
            model.addAttribute("adicional", adicionalesModel);
            return "editarAdicional"; // Asegúrate de que el archivo se llama "editarCliente.html"
        }
        return "redirect:/error"; // Si no se encuentra el cliente
    }
    public List<AdicionalesModel> listarAdicionales(List<Long> ids) {
        return adicionalService.obtenerAdicionales();
    }

    @PostMapping("/guardar")
    public String guardarAdicional(@ModelAttribute AdicionalesModel adicional) {
        adicionalService.guardarAdicional(adicional);
        return "redirect:/admin/adicionales/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAdicional(@PathVariable Long id) {
        adicionalService.eliminarAdicional(id);
        return "redirect:/admin/adicionales/";
    }


}
