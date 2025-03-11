package com.example.demo.Service;

import com.example.demo.Model.AdicionalesModel;
import com.example.demo.Repository.AdicionalesRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    @PostConstruct
    public void init() {

            List<AdicionalesModel> adicionales = Arrays.asList(
                    new AdicionalesModel("Salsa Teriyaki", 5000.0),
                    new AdicionalesModel("Salsa de Anguila", 6000.0),
                    new AdicionalesModel("Salsa Spicy Mayo", 4500.0),
                    new AdicionalesModel("Salsa Ponzu", 5500.0),
                    new AdicionalesModel("Jengibre Encurtido", 3000.0),
                    new AdicionalesModel("Wasabi Extra", 2500.0),
                    new AdicionalesModel("Aguacate", 7000.0),
                    new AdicionalesModel("Pepino", 5000.0),
                    new AdicionalesModel("Cebollín", 4000.0),
                    new AdicionalesModel("Queso Crema", 8000.0),
                    new AdicionalesModel("Tobiko (Huevas de pescado)", 12000.0),
                    new AdicionalesModel("Camaron Tempura", 10000.0),
                    new AdicionalesModel("Alga Nori Extra", 3500.0),
                    new AdicionalesModel("Arroz Adicional", 5000.0),
                    new AdicionalesModel("Sésamo Tostado", 4000.0)
            );
            adicionalesRepository.saveAll(adicionales);
        }
    }



