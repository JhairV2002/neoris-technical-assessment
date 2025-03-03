package db.repositorio.financiero.dtos;

import db.repositorio.financiero.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ClienteDTO {
    private String nombres;
    private Genero genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
