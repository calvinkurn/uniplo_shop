package com.uniploshop.network.model.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.uniploshop.network.model.LoginResponseModel
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