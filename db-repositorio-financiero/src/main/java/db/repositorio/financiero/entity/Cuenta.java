package db.repositorio.financiero.entity;

import db.repositorio.financiero.enums.CuentaTipo;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Random;

@Entity
@Data
@Table(name = "cuentas", schema = "cuenta")
public class Cuenta {
    @Id
    @Column(nullable = false, length = 10)
    private String numCuenta;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CuentaTipo tipoCuenta;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal saldoInicial;
    @Column(nullable = false)
    private boolean estado;
    @Column(nullable = false)
    private Long clienteId;
}