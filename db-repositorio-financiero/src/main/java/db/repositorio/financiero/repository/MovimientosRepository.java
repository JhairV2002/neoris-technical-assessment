package db.repositorio.financiero.repository;

import db.repositorio.financiero.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientosRepository extends JpaRepository<Movimiento, Long> {
}