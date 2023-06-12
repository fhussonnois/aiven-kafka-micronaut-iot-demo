package dev.iot.sender.config;

import io.micronaut.context.ApplicationContext;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

@MicronautTest
class DeviceMeasureConfigurationTest {

    @Inject
    ApplicationContext ctx;

    @Test
    void testNotEmptyConfiguration() {
        // When
        Collection<DeviceMeasureConfiguration> configs = ctx.getBeansOfType(DeviceMeasureConfiguration.class);
        // Then
        Assertions.assertNotNull(configs);
        Assertions.assertFalse(configs.isEmpty());
    }
}