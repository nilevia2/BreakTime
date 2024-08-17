package data

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SetIntConverter {
    @TypeConverter
    fun fromString(value: String): Set<Int> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromSet(set: Set<Int>): String {
        return Json.encodeToString(set)
    }
}