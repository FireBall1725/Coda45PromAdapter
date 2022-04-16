package ca.fireball1725.coda45promadapter.coda45

import ca.fireball1725.coda45promadapter.models.ModelSystemInformation
import ca.fireball1725.coda45promadapter.util.APIProcessor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SystemInformation {
    fun getSystemInformation(): ModelSystemInformation? {
        val statusLogResponse = APIProcessor.callApi(listOf("data", "getSysInfo.asp"), "GET") ?: return null

        if (statusLogResponse.code != 200)
            return null

        val jsonResponse = statusLogResponse.body!!.string()

        return try {
            val type: Type = object : TypeToken<List<ModelSystemInformation?>?>() {}.getType()
            val result: List<ModelSystemInformation> = Gson().fromJson(jsonResponse, type)
            if (result.isNotEmpty())
                return result[0]
            return null
        } catch (e: java.lang.Exception) {
            // handle exception
            null
        }
    }
}
