package data

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory
import data.instantiateImpl
import data.ConfigDatabase

fun getConfigDatabase(): ConfigDatabase {
    val dbFile = NSHomeDirectory()+"/config.db"
    return Room.databaseBuilder<ConfigDatabase>(
        name = dbFile,
        { ConfigDatabase::class.instantiateImpl()}
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}