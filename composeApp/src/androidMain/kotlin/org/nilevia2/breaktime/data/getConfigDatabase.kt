package org.nilevia2.breaktime.data

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.ConfigDatabase

fun getConfigDatabase(context: Context): ConfigDatabase{
    val dbFile = context.getDatabasePath("config.db")
    return Room.databaseBuilder<ConfigDatabase>(
        context.applicationContext,
        dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}