package db.repositorio.financiero.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class MovimientoResDTO {
    private Date fecha;
    private String numCuenta;
    private String tipoMovimiento;
    private BigDecimal valor;
    private BigDecimal saldo;
}
