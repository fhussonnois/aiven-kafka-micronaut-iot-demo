package dev.iot.sender.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;

import java.util.UUID;

/**
 * Represents information about a device.
 *
 * @param deviceId      the device id.
 * @param deviceName    the device name.
 */
@Serdeable
@ReflectiveAccess
@Builder
public record DeviceInfo (
        @JsonProperty("deviceId") UUID deviceId,
        @JsonProperty("deviceName") String deviceName){ }
