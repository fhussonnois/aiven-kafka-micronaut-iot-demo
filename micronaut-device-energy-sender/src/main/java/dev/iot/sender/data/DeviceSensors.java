package dev.iot.sender.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.ReflectiveAccess;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;

@Serdeable
@ReflectiveAccess
@Builder
public record DeviceSensors(@JsonProperty("watts") double watts) {
}
