package jhair.vasquez.ms.core.cuentas.movimientos.communication;

import db.repositorio.financiero.dtos.ClienteResponseDTO;
import jhair.vasquez.ms.core.cuentas.movimientos.cons.KafkaCons;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class KafkaConsumerClient {

    private final Map<String, CompletableFuture<ClienteResponseDTO>> responseFutures = new ConcurrentHashMap<>();
    // Consumidor de respuestas
    @KafkaListener(topics = KafkaCons.TOPIC_RESPONSE, groupId = KafkaCons.GROUP_ID)
    public void listenClienteResponse(ConsumerRecord<String, ClienteResponseDTO> record) {
        ClienteResponseDTO response = record.value();
        String correlationId = response.getCorrelationId();
        log.info("Recibida respuesta de Kafka para correlationId: {}", correlationId);
        KafkaPetitionManager.getInstance().completeResponse(correlationId, response);
    }
}