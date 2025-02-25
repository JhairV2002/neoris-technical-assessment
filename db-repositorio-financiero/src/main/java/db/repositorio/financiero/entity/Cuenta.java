package db.repositorio.financiero.entity;

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
    private String tipoCuenta;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal saldoInicial;
    @Column(nullable = false)
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @PrePersist
    public void prePersist() {
        if (this.numCuenta == null || this.numCuenta.isEmpty()) {
            this.numCuenta = generateNumCuenta(10);
        }
        this.estado = true;
    }

    public String generateNumCuenta(int length) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) builder.append(random.nextInt(10));

        return builder.toString();

    }
}