package db.repositorio.financiero.classes;

import db.repositorio.financiero.enums.Genero;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
public class Persona {
    private String nombres;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    private Integer edad;
    @Column(nullable = false, unique = true)
    private String identificacion;
    private String direccion;
    private String telefono;
}