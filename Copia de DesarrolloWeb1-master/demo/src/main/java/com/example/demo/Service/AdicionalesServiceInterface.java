package com.example.demo.Service;
import com.example.demo.Model.AdicionalesModel;
import java.util.List;
import java.util.Optional;

public interface AdicionalesServiceInterface {

        List<AdicionalesModel> obtenerAdicionales();

        Optional<AdicionalesModel> obtenerAdicionalPorId(Long id);

        AdicionalesModel guardarAdicional(AdicionalesModel adicional);

        void eliminarAdicional(Long id);

        List<AdicionalesModel> obtenerAdicionalesPorIds(List<Long> ids);


}
