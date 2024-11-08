package com.uniploshop.network.model

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class LoginModelDeserialize: JsonDeserializer<LoginResponseModel> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LoginResponseModel {
        return if (json?.isJsonPrimitive == true && json.asJsonPrimitive?.isString == true) {
            LoginResponseModel(errorMsg = json.asString)
        } else {
            val item = json?.asJsonObject?.get("token")?.asString
            LoginResponseModel(token = item)
        }
    }
}