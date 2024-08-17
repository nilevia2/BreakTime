package model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Entity
@Serializable
data class Config(
    val startWork: String,
    val endWork: String,
    val lunchTime: String,
    val breakInterval: String,
    val selectedDays: Set<Int>,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

)