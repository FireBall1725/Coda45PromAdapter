package ca.fireball1725.coda45promadapter.factories

import io.micrometer.core.instrument.Clock
import io.micrometer.prometheus.PrometheusMeterRegistry
import io.micronaut.configuration.metrics.micrometer.ExportConfigurationProperties
import io.micronaut.configuration.metrics.micrometer.MeterRegistryFactory.MICRONAUT_METRICS_EXPORT
import io.micronaut.context.annotation.Factory
import io.prometheus.client.CollectorRegistry
import jakarta.inject.Singleton
import java.util.Properties

@Factory
class PrometheusMeterRegistryFactory {
    @Singleton
    fun prometheusConfig(exportConfigurationProperties: ExportConfigurationProperties, collectorRegistry: CollectorRegistry): PrometheusMeterRegistry {
        val exportConfig: Properties = exportConfigurationProperties.export
        return PrometheusMeterRegistry(exportConfig::getProperty, collectorRegistry, Clock.SYSTEM)
    }

    companion object {
        val PROMETHEUS_CONFIG: String = "$MICRONAUT_METRICS_EXPORT.prometheus"
        val PROMETHEUS_ENABLED = "$PROMETHEUS_CONFIG.enabled"
    }
}
