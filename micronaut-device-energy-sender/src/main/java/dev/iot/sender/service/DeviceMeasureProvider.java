package dev.iot.sender.service;

import dev.iot.sender.data.DeviceInfo;
import dev.iot.sender.data.DeviceMeasure;
import dev.iot.sender.data.DeviceSensors;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.UUID;

public final class DeviceMeasureProvider {

    private static final Random random = new Random();

    private final UUID ownerId;
    private final String ownerName;
    private final UUID deviceId;
    private final String deviceName;
    private final double deviceWatts;

    /**
     * Creates a new {@link DeviceMeasureProvider} instance.
     *
     * @param ownerId     the device owner id.
     * @param ownerName   the device owner name.
     * @param deviceId    the device id.
     * @param deviceName  the device name.
     * @param deviceWatts the device watts.
     */
    public DeviceMeasureProvider(UUID ownerId,
                                 String ownerName,
                                 UUID deviceId,
                                 String deviceName,
                                 double deviceWatts) {
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceWatts = deviceWatts;
    }

    /**
     * Generates a measure for the device managed by the provider.
     *
     * @return a new {@link DeviceMeasure}.
     */
    public DeviceMeasure getMeasure() {
        return new DeviceMeasure(
                ownerId,
                ownerName,
                new DeviceInfo(deviceId, deviceName),
                new DeviceSensors(currentWatts())
        );
    }

    /**
     * @return the current device power consumption in Watt.
     */
    private double currentWatts() {
        double val = randomPowerFactor() * deviceWatts;
        return new BigDecimal(val).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * @return a random factor to simulate power variation.
     */
    private double randomPowerFactor() {
        return random.nextDouble(0.6, 1);
    }
}
