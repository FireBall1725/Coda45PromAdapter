package ca.fireball1725.coda45promadapter.models

import com.google.gson.annotations.SerializedName

data class ModelDocsisProvisioning(
    val hwInit: String,
    val findDownstream: String,
    val ranging: String,
    val dhcp: String,
    @SerializedName("timeOfday")
    val timeOfDay: String,
    val downloadCfg: String,
    val registration: String,
    val eaeStatus: String,
    val bpiStatus: String,
    val networkAccess: String,
    val trafficStatus: String
)
