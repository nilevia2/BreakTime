package data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import model.Config

@Dao
interface ConfigDao {
    @Query("SELECT * FROM config ORDER BY id DESC")
    fun getConfig(): Flow<Config?>

    @Delete
    suspend fun delete(configuration: Config)
    @Upsert
    suspend fun upsert(configuration: Config)
}