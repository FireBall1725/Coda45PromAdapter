package ca.fireball1725.coda45promadapter.factories

import io.micronaut.context.annotation.Factory
import io.prometheus.client.CollectorRegistry
import jakarta.inject.Singleton

@Factory
class CollectorRegistryFactory {
    @Singleton
    fun getCollector(): CollectorRegistry {
        return CollectorRegistry.defaultRegistry
    }
}
