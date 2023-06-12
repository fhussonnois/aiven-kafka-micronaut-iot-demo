package dev.iot.sender.config;

import io.micronaut.context.annotation.ConfigurationInject;
import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.EachProperty;

/**
 * This class represents a devices configuration used to produce events.
 */
@EachProperty(value = "data.devices", list = true)
public class DeviceMeasureConfiguration {

    private final String ownerName;
    private final DeviceConfiguration device;

    @ConfigurationInject
    public DeviceMeasureConfiguration(String ownerName,
                                      DeviceConfiguration device) {
        this.ownerName = ownerName;
        this.device = device;
    }

    public String getOwner() {
        return ownerName;
    }

    public DeviceConfiguration getDevice() {
        return device;
    }

    @ConfigurationProperties(value = "device")
    public static class DeviceConfiguration {

        private final String name;
        private final double watts;

        @ConfigurationInject
        public DeviceConfiguration(String name, double watts) {
            this.name = name;
            this.watts = watts;
        }

        public String getName() {
            return name;
        }

        public double getWatts() {
            return watts;
        }
    }
}
