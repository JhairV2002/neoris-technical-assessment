package db.repositorio.financiero.entity;

import db.repositorio.financiero.classes.Persona;
import db.repositorio.financiero.enums.Genero;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clientes", schema = "persona")
public class Cliente extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteId;
    @Column(nullable = false)
    private String contrasenia;
    @Column(nullable = false)
    private boolean estado = true;
}