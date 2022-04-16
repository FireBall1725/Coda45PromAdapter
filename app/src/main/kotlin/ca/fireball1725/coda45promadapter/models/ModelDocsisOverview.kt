package ca.fireball1725.coda45promadapter.models

import com.google.gson.annotations.SerializedName

data class ModelDocsisOverview(
    @SerializedName("Configname")
    val configName: String,
    @SerializedName("ConfignameDisplay")
    val configNameDisplay: String,
    @SerializedName("NetworkAccess")
    val networkAccess: String,
    @SerializedName("CmIpAddress")
    val cmIpAddress: String,
    @SerializedName("CmNetMask")
    val cmNetMask: String,
    @SerializedName("CmGateway")
    val cmGateway: String,
    @SerializedName("CmIpLeaseDuration")
    val cmIpLeaseDuration: String
)
