package ms.core.persona.cliente.service.interfaces;

import db.repositorio.financiero.entity.Cliente;
import ms.core.persona.cliente.base.GenericResponse;
import ms.core.persona.cliente.customExceptions.RecordNotFoundException;

import java.util.List;

public interface ClienteService {
    GenericResponse<List<Cliente>> findAll();
    GenericResponse<List<Cliente>> findByEstado(String estado);
    GenericResponse<Cliente> findById(Long id) throws RecordNotFoundException;
    GenericResponse<Cliente> findbyIdentificacion(String identificacion);
    GenericResponse<Cliente> save(Cliente cliente) throws Exception;
    GenericResponse<Cliente> update(Cliente cliente) throws Exception;
    GenericResponse<String> delete(Long id);
}
