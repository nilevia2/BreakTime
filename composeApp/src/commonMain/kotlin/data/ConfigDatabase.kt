package data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import model.Config

@Database(
    entities = [Config::class],
    version = 2
)
@TypeConverters(SetIntConverter::class)
abstract class ConfigDatabase: RoomDatabase() {

    abstract fun configDao(): ConfigDao

}