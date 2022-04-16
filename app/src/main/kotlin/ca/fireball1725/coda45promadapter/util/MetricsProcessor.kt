package ca.fireball1725.coda45promadapter.util

import ca.fireball1725.coda45promadapter.coda45.DocsisEvent
import ca.fireball1725.coda45promadapter.coda45.DocsisProvisioning
import ca.fireball1725.coda45promadapter.coda45.DocsisWan
import ca.fireball1725.coda45promadapter.coda45.LanPortStatus
import ca.fireball1725.coda45promadapter.coda45.SystemInformation
import jakarta.inject.Singleton

@Singleton
class MetricsProcessor {
    // this will be what kicks off the metrics collection, should be called by a timer...
    private val systemInformation = SystemInformation()
    private val docsisProvisioning = DocsisProvisioning()
    private val docsisWan = DocsisWan()
    private val docsisEvent = DocsisEvent()
    private val lanPortStatus = LanPortStatus()

    fun processMetrics() {
        docsisEvent.process()
        docsisProvisioning.process()
        docsisWan.process()
    }
}
