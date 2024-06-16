package model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Config(
    val startWork: String,
    val endWork: String,
    val lunchTime: String,
    val breakInterval: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

)