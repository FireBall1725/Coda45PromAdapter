package ca.fireball1725.coda45promadapter.models

import com.google.gson.annotations.SerializedName

data class ModelSystemInformation(
    val hwVersion: String,
    val swVersion: String,
    val serialNumber: String,
    val rfMac: String,
    val wanIp: String,
    val systemUptime: String,
    val systemTime: String,
    val timezone: String,
    @SerializedName("WRecPkt")
    val wRecPkt: String,
    @SerializedName("WSendPkt")
    val wSendPkt: String,
    val lanIp: String,
    @SerializedName("LRecPkt")
    val lRecPkt: String,
    @SerializedName("LSendPkt")
    val lSendPkt: String
)
