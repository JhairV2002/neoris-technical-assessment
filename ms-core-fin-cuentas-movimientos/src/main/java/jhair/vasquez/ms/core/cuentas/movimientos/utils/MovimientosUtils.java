package jhair.vasquez.ms.core.cuentas.movimientos.utils;

import jhair.vasquez.ms.core.cuentas.movimientos.cons.MovimientosConstants;
import jhair.vasquez.ms.core.cuentas.movimientos.customExceptions.InsufficientFundsException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MovimientosUtils {
    public BigDecimal calcularSaldo(BigDecimal saldoInicial, BigDecimal monto) throws InsufficientFundsException {
            if (monto == null) {
                throw new IllegalArgumentException("El monto no puede ser nulo");
            }

            boolean isMontoNegativo = monto.compareTo(BigDecimal.ZERO) < 0;
            boolean isMontoMayorQueSaldo = monto.abs().compareTo(saldoInicial) > 0;

            if (isMontoNegativo && isMontoMayorQueSaldo) {
                throw new InsufficientFundsException("El monto no puede ser mayor al saldo inicial");
            }

            return isMontoNegativo ? saldoInicial.subtract(monto.abs()) : saldoInicial.add(monto);
        }

    public String getMovementType(BigDecimal monto) {
        return monto.compareTo(BigDecimal.ZERO) < 0 ? MovimientosConstants.RETIRO_DINERO_MSG : MovimientosConstants.DEPOSITO_DINERO_MSG;
    }
}
