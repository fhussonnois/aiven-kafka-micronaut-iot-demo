package dev.iot.sender.service;

import dev.iot.sender.data.DeviceMeasure;
import dev.iot.sender.data.DeviceMeasureKey;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * This class is responsible to periodically send device measures into Kafka.
 */
@Singleton
public class DeviceMeasureSender {

    private static final Logger LOG = LoggerFactory.getLogger(DeviceMeasureSender.class);

    private final List<DeviceMeasureProvider> providers;

    private final DeviceMeasureKafkaClient kafkaClient;

    /**
     * Creates a new {@link DeviceMeasureSender} instance.
     *
     * @param kafkaClient the kafka client.
     * @param providers   the list of {@link DeviceMeasureProvider}.
     */
    public DeviceMeasureSender(final DeviceMeasureKafkaClient kafkaClient,
                               final List<DeviceMeasureProvider> providers) {
        this.kafkaClient = Objects.requireNonNull(kafkaClient, "kafkaClient must not be null");
        this.providers = Objects.requireNonNull(providers, "provides must not be null");
        if (this.providers.isEmpty()) {
            throw new IllegalArgumentException("At-least one provider should be configured");
        }
    }

    @Scheduled(fixedDelay = "5s", initialDelay = "5s")
    void send() {
        // (1) Generate all device measurements
        List<DeviceMeasure> measures = providers.stream()
                .map(DeviceMeasureProvider::getMeasure)
                .toList();

        // (2) Send event to Kafka
        for (DeviceMeasure measure : measures) {
            DeviceMeasureKey key = new DeviceMeasureKey(measure.ownerId());
            CompletableFuture<RecordMetadata> result = kafkaClient.sendMeasure(key, measure);
            result.whenCompleteAsync((metadata, exception) -> {
                // (3) Log event sent successfully
                if (metadata != null) {
                    LOG.info("Sent event {} into topic={}-{} at offset={}",
                            measure,
                            metadata.topic(),
                            metadata.partition(),
                            metadata.offset()
                    );
                } else {
                    // (3') or log error if something goes wrong
                    LOG.info("Failed to sent event {} - data is discard: Error: {}",
                            measure,
                            exception.getLocalizedMessage()
                    );
                }
            });
        }
    }
}
