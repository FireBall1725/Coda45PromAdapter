package ca.fireball1725.coda45promadapter.coda45

import ca.fireball1725.coda45promadapter.models.ModelLanPort
import ca.fireball1725.coda45promadapter.util.APIProcessor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class LanPortStatus {
    fun getLanPortStatus(): List<ModelLanPort>? {
        val statusLogResponse = APIProcessor.callApi(listOf("data", "getLinkStatus.asp"), "GET") ?: return null

        if (statusLogResponse.code != 200)
            return null

        val jsonResponse = statusLogResponse.body!!.string()

        return try {
            val type: Type = object : TypeToken<List<ModelLanPort?>?>() {}.getType()
            Gson().fromJson(jsonResponse, type)
        } catch (e: java.lang.Exception) {
            // handle exception
            null
        }
    }
}
