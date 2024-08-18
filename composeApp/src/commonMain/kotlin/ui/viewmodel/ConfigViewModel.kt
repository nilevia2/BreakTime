package ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.ConfigRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import model.Config
import notification.NotificationManager
import kotlin.time.DurationUnit

class ConfigViewModel(
    private val configRepository: ConfigRepository
) : ViewModel() {

    private val _configurationState = MutableStateFlow<Config?>(null)
    val configurationState: StateFlow<Config?> = _configurationState.asStateFlow()

    init {
        getConfig()
    }
    private fun getConfig(){
        viewModelScope.launch {
            configRepository.getConfiguration().collect {
                _configurationState.value = it
            }
        }
    }


    fun saveConfig(config: Config){
        viewModelScope.launch(Dispatchers.IO) {
            configRepository.saveConfiguration(config)
        }
        val startTime = Clock.System.now().toEpochMilliseconds()
        val endTime = startTime.plus(1000*60)
        val intervalMinutes: Long = 1
        NotificationManager.updateScheduling(startTime, endTime, intervalMinutes)

    }
}