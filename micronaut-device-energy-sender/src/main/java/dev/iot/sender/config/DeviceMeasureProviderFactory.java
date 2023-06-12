package dev.iot.sender.config;

import dev.iot.sender.service.DeviceMeasureProvider;
import io.micronaut.context.annotation.EachBean;
import io.micronaut.context.annotation.Factory;

import java.util.UUID;

/**
 * Factory class.
 */
@Factory
public class DeviceMeasureProviderFactory {

    /**
     * Creates a new {@link DeviceMeasureProvider} instance for each owner/device.
     *
     * @param config a device.
     * @return the list of all {@link DeviceMeasureProvider}.
     */
    @EachBean(DeviceMeasureConfiguration.class)
    public DeviceMeasureProvider deviceMeasureProviders(DeviceMeasureConfiguration config) {
        return new DeviceMeasureProvider(
                UUID.randomUUID(),
                config.getOwner(),
                UUID.randomUUID(),
                config.getDevice().getName(),
                config.getDevice().getWatts()
        );
    }
}
