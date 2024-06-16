package data

import kotlinx.coroutines.flow.Flow
import model.Config

class ConfigRepository(private val configurationDao: ConfigDao) {
    suspend fun getConfiguration(): Flow<Config?> {
        return configurationDao.getConfig()
    }

    suspend fun saveConfiguration(configuration: Config) {
        configurationDao.upsert(configuration)
    }
}