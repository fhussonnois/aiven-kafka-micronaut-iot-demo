package dev.iot.sender.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

/**
 * Represents a timestamped device measurements event.
 *
 * @param timestamp the device measure timestamp.
 * @param ownerId   the device ownerId uuid.
 * @param ownerName the id of client that owned the device.
 * @param deviceInfo      the device information.
 * @param deviceSensors   the device sensors.
 */
@Serdeable
@ReflectiveAccess
@Builder
public record DeviceMeasure(
        @JsonProperty("timestamp")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "UTC")
        Instant timestamp,
        @JsonProperty("ownerId")
        UUID ownerId,
        @JsonProperty("ownerName")
        String ownerName,
        @JsonProperty("deviceInfo")
        DeviceInfo deviceInfo,
        @JsonProperty("deviceSensors")
        DeviceSensors deviceSensors
) {

    public DeviceMeasure(UUID ownerId, String ownerName, DeviceInfo info, DeviceSensors sensors) {
        this(Instant.now(), ownerId, ownerName, info, sensors);
    }
}
