package db.repositorio.financiero.dtos;

import lombok.Data;

@Data
public class ClienteRequestDTO {
    private Long clienteId;
    private String correlationId; // Para identificar la respuesta
}
