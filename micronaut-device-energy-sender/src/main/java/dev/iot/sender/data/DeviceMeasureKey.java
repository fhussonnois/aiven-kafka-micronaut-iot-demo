package dev.iot.sender.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;

@Serdeable
public record DeviceMeasureKey(@JsonProperty("id") UUID ownerID) { }
