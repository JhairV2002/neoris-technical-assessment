package db.repositorio.financiero.dtos;

import db.repositorio.financiero.entity.Cuenta;
import db.repositorio.financiero.entity.Movimiento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuentaConMovimientoDTO {
    private Cuenta cuenta;
    private BigDecimal saldoActual; // Calculado desde el último movimiento
    private List<Movimiento> movimientos; // Movimientos en el rango de fechas
}
