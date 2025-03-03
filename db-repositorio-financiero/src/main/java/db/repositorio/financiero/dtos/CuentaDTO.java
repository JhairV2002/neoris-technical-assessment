package db.repositorio.financiero.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class CuentaDTO {
    private String numCuenta;
    private String tipoCuenta;
    private BigDecimal saldoInicial;
    private boolean estado;
}
