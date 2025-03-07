package com.example.demo.Service;

import com.example.demo.Model.AdicionalesModel;
import com.example.demo.Repository.AdicionalesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdicionalesService {

    private final AdicionalesRepository adicionalesRepository;
    private final ProductoService productoService;
    public AdicionalesService(AdicionalesRepository adicionalesRepository, ProductoService productoService) {
        this.adicionalesRepository = adicionalesRepository;
        this.productoService = productoService;
    }

    public List<AdicionalesModel> obtenerAdicionales() {
        return adicionalesRepository.findAll();
    }

    // Obtener un adicional por ID
    public Optional<AdicionalesModel> obtenerAdicionalPorId(Long id) {
        return adicionalesRepository.findById(id);
    }

    // Guardar o actualizar un adicional
    public AdicionalesModel guardarAdicional(AdicionalesModel adicional) {

        return adicionalesRepository.save(adicional);
    }

    // Eliminar un adicional por ID
    public void eliminarAdicional(Long id) {

        productoService.eliminarAdicionalDeTodosLosProductos(id);
        adicionalesRepository.deleteById(id);
    }




}
