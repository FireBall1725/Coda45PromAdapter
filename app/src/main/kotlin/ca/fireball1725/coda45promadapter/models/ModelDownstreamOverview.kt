package ca.fireball1725.coda45promadapter.models

data class ModelDownstreamOverview(
    val portId: String,
    val frequency: String,
    val modulation: String,
    val signalStrength: String,
    val snr: String,
    val dsoctets: String,
    val correcteds: String,
    val uncorrect: String,
    val channelId: String
)
