package db.repositorio.financiero.dtos;

import db.repositorio.financiero.entity.Cliente;
import lombok.Data;

@Data
public class ClienteResponseDTO {
    private Cliente cliente;
    private String correlationId;
}
