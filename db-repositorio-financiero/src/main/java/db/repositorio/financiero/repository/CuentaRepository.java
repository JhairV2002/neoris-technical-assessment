package db.repositorio.financiero.repository;

import db.repositorio.financiero.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    Cuenta findCuentaByNumCuenta(String numCuenta);
}
