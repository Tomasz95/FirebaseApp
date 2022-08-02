package pl.gda.wsb.firebaseapp.fragments.dishes.adapters

import android.util.Log
import com.google.gson.*
import kotlinx.serialization.json.Json
import org.json.JSONArray
import org.xml.sax.Parser
import pl.gda.wsb.firebaseapp.fragments.dishes.DishResponse
import java.lang.reflect.Type

class DishResponseAdapter : JsonDeserializer<DishResponse>, JsonSerializer<DishResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): DishResponse? {
        //
//        val obj = Json
//        val jsonWithoutFirstAndLast = json!!.asString.toString().substring(2, json!!.asString.length - 1)
//        Log.d("valuesadapter", jsonWithoutFirstAndLast.toString())
        Log.d("msgjsonvalue", json.toString())
        val inputJson = Gson().fromJson(json, DishResponse::class.java)
        return DishResponse(
            inputJson.id,
            inputJson.addresses,
            inputJson.description,
            inputJson.ingredients,
            inputJson.name,
            inputJson.restaurant,
            inputJson.web
        )
    }

    override fun serialize(
        src: DishResponse?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return when (src) {
            null -> JsonNull.INSTANCE
            else -> JsonPrimitive(src.toString())
        }
    }


}

