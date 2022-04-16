package ca.fireball1725.coda45promadapter.models

import com.google.gson.annotations.SerializedName

data class ModelOfdmDownstreamOverview(
    val receive: String,
    @SerializedName("ffttype")
    val fftType: String,
    @SerializedName("Subcarr0freqFreq")
    val subcarr0Freq: String,
    @SerializedName("plclock")
    val plcLock: String,
    @SerializedName("ncplock")
    val ncpLock: String,
    @SerializedName("mdc1lock")
    val mdc1Lock: String,
    @SerializedName("plcpower")
    val plcPower: String
)
