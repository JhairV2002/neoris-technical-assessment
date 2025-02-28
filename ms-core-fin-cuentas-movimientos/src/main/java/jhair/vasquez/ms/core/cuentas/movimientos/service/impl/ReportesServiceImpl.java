package jhair.vasquez.ms.core.cuentas.movimientos.service.impl;

import db.repositorio.financiero.dtos.ClienteResponseDTO;
import db.repositorio.financiero.dtos.CuentaConMovimientoDTO;
import db.repositorio.financiero.dtos.ReporteDTO;
import db.repositorio.financiero.entity.Cuenta;
import db.repositorio.financiero.entity.Movimiento;
import db.repositorio.financiero.repository.CuentaRepository;
import db.repositorio.financiero.repository.MovimientosRepository;
import jhair.vasquez.ms.core.cuentas.movimientos.communication.KafkaProducerClient;
import jhair.vasquez.ms.core.cuentas.movimientos.customExceptions.RecordNotFound;
import jhair.vasquez.ms.core.cuentas.movimientos.service.interfaces.CuentaService;
import jhair.vasquez.ms.core.cuentas.movimientos.service.interfaces.ReportesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ReportesServiceImpl implements ReportesService{
    private final MovimientosRepository movimientosRepository;
    private final CuentaRepository cuentaRepository;
    private final KafkaProducerClient kafkaProducerClient;

    @Override
    public ReporteDTO findMovimientosByCuentaNumAndFechaRange(Long clienteId, Date fechaInicio, Date fechaFin) throws RecordNotFound {
        if (fechaInicio.after(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la fecha de fin");
        }

        // Validar la existencia del cliente usando Kafka
        log.info("Validando existencia del cliente con ID: {}", clienteId);
        ClienteResponseDTO clienteResponse = kafkaProducerClient.fetchCliente(clienteId);
        if (clienteResponse.getCliente() == null) {
            log.warn("Cliente con ID {} no encontrado en ms-core-persona-cliente", clienteId);
            throw new RecordNotFound("No se encontró el cliente con ID: " + clienteId);
        }
        log.info("Cliente con ID {} validado exitosamente", clienteId);

        // Obtener todas las cuentas del cliente
        List<Cuenta> cuentas = cuentaRepository.findCuentaByClienteId(clienteId)
                .orElseThrow(() -> new RecordNotFound("No se encontraron cuentas para el cliente con ID " + clienteId));

        // Construir el reporte
        List<CuentaConMovimientoDTO> cuentasConMovimientos = cuentas.stream()
                .map(cuenta -> {
                    // Obtener movimientos en el rango de fechas
                    List<Movimiento> movimientos = movimientosRepository
                            .findMovimientoByCuentaNumAndFechaBetween(cuenta.getNumCuenta(), fechaInicio, fechaFin);

                    // Calcular saldo actual (último movimiento o saldo inicial si no hay movimientos)
                    BigDecimal saldoActual = movimientosRepository
                            .findMovimientoByCuentaNumOrderByFechaDesc(cuenta.getNumCuenta())
                            .stream()
                            .map(Movimiento::getSaldo)
                            .findFirst()
                            .orElse(cuenta.getSaldoInicial());

                    return CuentaConMovimientoDTO.builder()
                            .cuenta(cuenta)
                            .saldoActual(saldoActual)
                            .movimientos(movimientos)
                            .build();
                })
                .collect(Collectors.toList());

        return ReporteDTO.builder()
                .clienteId(clienteId)
                .cuentas(cuentasConMovimientos)
                .build();
    }
}
