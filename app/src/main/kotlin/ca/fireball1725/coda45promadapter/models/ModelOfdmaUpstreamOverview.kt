package ca.fireball1725.coda45promadapter.models

import com.google.gson.annotations.SerializedName

data class ModelOfdmaUpstreamOverview(
    @SerializedName("uschindex")
    val channelIndex: String,
    val state: String,
    val digAtten: String,
    val digAttenBo: String,
    val channelBw: String,
    val repPower: String,
    @SerializedName("repPower1_6")
    val repPower16: String,
    val fftVal: String
)
