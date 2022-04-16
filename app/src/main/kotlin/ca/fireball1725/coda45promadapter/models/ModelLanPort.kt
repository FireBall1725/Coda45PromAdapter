package ca.fireball1725.coda45promadapter.models

import com.google.gson.annotations.SerializedName

data class ModelLanPort(
    @SerializedName("LinkStatus")
    val linkStatus: String,
    @SerializedName("LinkDuplex")
    val linkDuplex: String,
    @SerializedName("LinkSpeed")
    val linkSpeed: String
)
