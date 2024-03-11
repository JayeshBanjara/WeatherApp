package com.jayesh.jayesh_practical_android_indianic.network

import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

//This class will generalize API call and check the result of API is success or not
//and returns according to it.
abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {

        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {

            val error = response.errorBody()?.string()

            val message = StringBuilder()

            error?.let {
                try {
                    message.append(JSONObject(it).getString("message"))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }

            throw ApiException(message.toString())
        }
    }
}