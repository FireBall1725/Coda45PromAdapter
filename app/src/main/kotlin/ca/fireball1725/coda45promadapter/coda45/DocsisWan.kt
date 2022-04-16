package ca.fireball1725.coda45promadapter.coda45

import ca.fireball1725.coda45promadapter.metrics.Metrics
import ca.fireball1725.coda45promadapter.models.ModelDocsisOverview
import ca.fireball1725.coda45promadapter.models.ModelDownstreamOverview
import ca.fireball1725.coda45promadapter.models.ModelOfdmDownstreamOverview
import ca.fireball1725.coda45promadapter.models.ModelOfdmaUpstreamOverview
import ca.fireball1725.coda45promadapter.models.ModelUpstreamOverview
import ca.fireball1725.coda45promadapter.util.APIProcessor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DocsisWan {
    private val metrics = Metrics.create()

    fun process() {
        processDownstreamOverview()
    }

    private fun getDocsisOverview(): ModelDocsisOverview? {
        val statusLogResponse = APIProcessor.callApi(listOf("data", "getCmDocsisWan.asp"), "GET") ?: return null

        if (statusLogResponse.code != 200)
            return null

        val jsonResponse = statusLogResponse.body!!.string()

        return try {
            val type: Type = object : TypeToken<List<ModelDocsisOverview?>?>() {}.getType()
            val result: List<ModelDocsisOverview> = Gson().fromJson(jsonResponse, type)
            if (result.isNotEmpty())
                return result[0]
            return null
        } catch (e: java.lang.Exception) {
            // todo: handle exception
            null
        }
    }

    private fun getDownstreamOverview(): List<ModelDownstreamOverview>? {
        val statusLogResponse = APIProcessor.callApi(listOf("data", "dsinfo.asp"), "GET") ?: return null

        if (statusLogResponse.code != 200)
            return null

        val jsonResponse = statusLogResponse.body!!.string()

        return try {
            val type: Type = object : TypeToken<List<ModelDownstreamOverview?>?>() {}.getType()
            Gson().fromJson(jsonResponse, type)
        } catch (e: java.lang.Exception) {
            // todo: handle exception
            null
        }
    }

    private fun getOfdmDownstreamOverview(): List<ModelOfdmDownstreamOverview>? {
        val statusLogResponse = APIProcessor.callApi(listOf("data", "dsofdminfo.asp"), "GET") ?: return null

        if (statusLogResponse.code != 200)
            return null

        val jsonResponse = statusLogResponse.body!!.string()

        return try {
            val type: Type = object : TypeToken<List<ModelOfdmDownstreamOverview?>?>() {}.getType()
            Gson().fromJson(jsonResponse, type)
        } catch (e: java.lang.Exception) {
            // handle exception
            null
        }
    }

    private fun getUpstreamOverview(): List<ModelUpstreamOverview>? {
        val statusLogResponse = APIProcessor.callApi(listOf("data", "usinfo.asp"), "GET") ?: return null

        if (statusLogResponse.code != 200)
            return null

        val jsonResponse = statusLogResponse.body!!.string()

        return try {
            val type: Type = object : TypeToken<List<ModelUpstreamOverview?>?>() {}.getType()
            Gson().fromJson(jsonResponse, type)
        } catch (e: java.lang.Exception) {
            // handle exception
            null
        }
    }

    private fun getOfdmaUpstreamOverview(): List<ModelOfdmaUpstreamOverview>? {
        val statusLogResponse = APIProcessor.callApi(listOf("data", "usofdminfo.asp"), "GET") ?: return null

        if (statusLogResponse.code != 200)
            return null

        val jsonResponse = statusLogResponse.body!!.string()

        return try {
            val type: Type = object : TypeToken<List<ModelOfdmaUpstreamOverview?>?>() {}.getType()
            Gson().fromJson(jsonResponse, type)
        } catch (e: java.lang.Exception) {
            // handle exception
            null
        }
    }

    private fun processDownstreamOverview() {
        val result = getDownstreamOverview()

        // Check to see if the list is null or empty, if so return
        if (result == null || result.isEmpty())
            return

        result.forEach {
            val frequency: Double? = it.frequency.toDoubleOrNull()
            val signalStrength: Double? = it.signalStrength.toDoubleOrNull()
            val snr: Double? = it.snr.toDoubleOrNull()
            val channelId: Double? = it.channelId.toDoubleOrNull()

            if (it.portId != null) {
                if (frequency != null)
                    metrics.docsis_downstream_frequency(it.portId).set(frequency)
                if (signalStrength != null)
                    metrics.docsis_downstream_signal_strength(it.portId).set(signalStrength)
                if (snr != null)
                    metrics.docsis_downstream_snr(it.portId).set(snr)
                if (channelId != null)
                    metrics.docsis_downstream_channelId(it.portId).set(channelId)
            }
        }
    }
}
