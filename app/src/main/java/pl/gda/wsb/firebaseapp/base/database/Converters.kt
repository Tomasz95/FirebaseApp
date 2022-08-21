package pl.gda.wsb.firebaseapp.base.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pl.gda.wsb.firebaseapp.fragments.home.entities.DishResponseRoom
import java.lang.reflect.Type
import java.util.*

class Converters {


    @TypeConverter
    fun stringToListServer(data: String?): List<String?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object :
            TypeToken<List<String?>?>() {}.type
        return Gson().fromJson<List<String?>>(data, listType)
    }

    @TypeConverter
    fun listServerToString(someObjects: List<String?>?): String? {
        return Gson().toJson(someObjects)
    }
}
//    }
//
//
//    @TypeConverter
//    fun fromString(value: String?): List<String> {
//
//        return Gson().fromJson(value, listType)
//    }
//
//    @TypeConverter
//    fun fromList(list: List<String?>?): String {
//        val gson = Gson()
//        return gson.toJson(list)
//    }
//}
//
//fun stringToWords(s : String) = s.trim().splitToSequence(' ')
//    .filter { it.isNotEmpty() } // or: .filter { it.isNotBlank() }
//    .toList()