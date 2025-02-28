package db.repositorio.financiero.repository;

import db.repositorio.financiero.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, String> {
    Cuenta findCuentaByNumCuenta(String numCuenta);
    Optional<List<Cuenta>> findCuentaByClienteId(Long clienteId);
}
