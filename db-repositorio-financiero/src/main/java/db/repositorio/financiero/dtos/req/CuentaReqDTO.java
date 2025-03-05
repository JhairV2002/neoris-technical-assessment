package db.repositorio.financiero.dtos.req;

import db.repositorio.financiero.enums.CuentaTipo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class CuentaReqDTO {
    private CuentaTipo tipoCuenta;
    private BigDecimal SaldoInicial;
}
