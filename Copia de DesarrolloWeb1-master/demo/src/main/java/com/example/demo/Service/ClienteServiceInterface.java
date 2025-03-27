package com.example.demo.Service;

import com.example.demo.Model.ClienteModel;
import java.util.List;
import java.util.Optional;

public interface ClienteServiceInterface {

    List<ClienteModel> obtenerClientes();

    ClienteModel obtenerClientePorId(Long id);

    ClienteModel guardarCliente(ClienteModel cliente);

    void eliminarCliente(Long id);

    ClienteModel buscarPorEmail(String email);
}
