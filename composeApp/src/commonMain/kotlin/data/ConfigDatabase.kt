package data

import androidx.room.Database
import androidx.room.RoomDatabase
import model.Config

@Database(
    entities = [Config::class],
    version = 1
)
abstract class ConfigDatabase: RoomDatabase() {

    abstract fun configDao(): ConfigDao

}