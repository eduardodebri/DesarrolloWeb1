package com.example.demo.Service;

import com.example.demo.Model.ClienteModel;
import com.example.demo.Repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements ClienteServiceInterface {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Obtener todos los clientes
    public List<ClienteModel> obtenerClientes() {
        return clienteRepository.findAll();
    }

    // Obtener un cliente por ID
    public ClienteModel obtenerClientePorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    // Guardar o actualizar un cliente
    public ClienteModel guardarCliente(ClienteModel cliente) {
        return clienteRepository.save(cliente);
    }

    // Eliminar un cliente por ID
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    // Buscar cliente por email (opcional si necesitas autenticación)
    public ClienteModel buscarPorEmail(String email) {
        Optional<ClienteModel> cliente = clienteRepository.findByEmail(email);
        return cliente.orElse(null);
    }
}
