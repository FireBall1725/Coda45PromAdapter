package ca.fireball1725.coda45promadapter.models

import com.google.gson.annotations.SerializedName

data class ModelUpstreamOverview(
    val portId: String,
    val frequency: String,
    val bandwidth: String,
    @SerializedName("modtype")
    val modulationType: String,
    val scdmaMode: String,
    val signalStrength: String,
    val channelId: String
)
