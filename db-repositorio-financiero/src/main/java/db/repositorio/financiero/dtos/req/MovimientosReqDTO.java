package db.repositorio.financiero.dtos.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class MovimientosReqDTO {
    private String numCuenta;
    private BigDecimal valor;
}
