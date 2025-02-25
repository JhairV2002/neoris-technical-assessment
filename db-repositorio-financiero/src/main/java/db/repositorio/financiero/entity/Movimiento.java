package db.repositorio.financiero.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Data
@Table(name = "movimientos", schema = "movimientos")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fecha;
    private String tipoMovimiento;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal saldo;

    // foreign key with Cuenta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "num_cuenta")
    private Cuenta cuenta;

}
