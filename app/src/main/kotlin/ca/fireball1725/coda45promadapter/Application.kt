package ca.fireball1725.coda45promadapter

import ca.fireball1725.coda45promadapter.util.MetricsProcessor
import io.micronaut.runtime.Micronaut
import java.util.Timer
import java.util.TimerTask

class Application {
    companion object {
        private val metricsProcessor: MetricsProcessor = MetricsProcessor()

        @JvmStatic
        fun main(args: Array<String>) {
            Micronaut.build()
                .args(*args)
                .packages("ca.firekatt.coda45promadapter")
                .start()

            Timer().scheduleAtFixedRate(
                object : TimerTask() {
                    override fun run() {
                        metricsProcessor.processMetrics()
                    }
                },
                5000,
                30000
            )
        }
    }
}
