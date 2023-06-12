package dev.iot.sender.service;

import dev.iot.sender.data.DeviceMeasure;
import dev.iot.sender.data.DeviceMeasureKey;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.context.annotation.Property;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.CompletableFuture;

/**
 * Default client to produce device measurements into an Apache Kafka topic.
 *
 * @see DeviceMeasure
 */
@KafkaClient(
        id = "${app.device.sender.kafka.client-id}",
        acks = KafkaClient.Acknowledge.ALL,
        properties = @Property(name = ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, value = "true")
)
public interface DeviceMeasureKafkaClient {

    @Topic("${app.device.sender.kafka.topic-source}")
    CompletableFuture<RecordMetadata> sendMeasure(@KafkaKey DeviceMeasureKey key, DeviceMeasure measure);
}
