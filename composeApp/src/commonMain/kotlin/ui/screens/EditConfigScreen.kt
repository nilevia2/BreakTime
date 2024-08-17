package ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import breaktime.composeapp.generated.resources.Res
import breaktime.composeapp.generated.resources.btn_change_config
import breaktime.composeapp.generated.resources.label_break_interval
import breaktime.composeapp.generated.resources.label_end_work
import breaktime.composeapp.generated.resources.label_minutes
import breaktime.composeapp.generated.resources.btn_save_config
import breaktime.composeapp.generated.resources.label_selected_days
import breaktime.composeapp.generated.resources.label_start_work
import model.Config
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import ui.components.DayCircle
import ui.components.DayOfWeek
import ui.components.NumberPicker
import ui.components.TimePicker
import ui.viewmodel.ConfigViewModel

@Composable
fun EditConfigScreen(
    viewModel: ConfigViewModel,
    navController: NavHostController,
    configId: String? = null
) {

    val configurationState = viewModel.configurationState.collectAsState()
    val config by remember { mutableStateOf(configurationState.value) }
    EditData(config) {
        viewModel.saveConfig(it)
        navController.navigateUp()
    }

}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun EditData(config: Config?, saveData: (Config) -> Unit = {}) {
    var startWork by remember(config) { mutableStateOf(config?.startWork ?: "09:00") }
    var endWork by remember(config) { mutableStateOf(config?.endWork ?: "17:00") }
    var lunchTime by remember(config) { mutableStateOf(config?.lunchTime ?: "60") }
    var breakInterval by remember(config) { mutableStateOf(config?.breakInterval ?: "15") }
    var selectedDays by remember(config) { mutableStateOf(config?.selectedDays ?: setOf<Int>()) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ){

        Spacer(modifier = Modifier.height(34.dp))
        Text(stringResource(Res.string.label_selected_days))
        Row(modifier = Modifier.padding(vertical = 16.dp)) {
            DayOfWeek.entries.forEach { day ->
                DayCircle(
                    dayOfWeek = day,
                    isSelected = selectedDays.contains(day.value),
                    isEditable = true,
                    onDayClick = { clickedDay ->
                        selectedDays = if (selectedDays.contains(clickedDay.value)) {
                            selectedDays - clickedDay.value
                        } else {
                            selectedDays + clickedDay.value
                        }
                    }
                )
                Spacer(modifier = Modifier.width(16.dp))
            }

        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(stringResource(Res.string.label_start_work))
        TimePicker(initialTime = startWork) { hour, minute ->
            startWork = "${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}"
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text(stringResource(Res.string.label_end_work))
// SOME PEOPLE WORKING MIDNIGHT SO NO NEED LIMITATION ANYMORE
//        val startHour = startWork.substringBefore(':').toIntOrNull() ?: 0
//        val startMinute = startWork.substringAfter(':').toIntOrNull() ?: 0
        TimePicker(
            initialTime = endWork,
//            minHour = startHour,
//            minMinute = startMinute
        ) { hour, minute ->
            endWork = "${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}"
        }
        Spacer(modifier = Modifier.height(20.dp))
//        NumberInput(
//            label = "Lunch Time (minutes)",
//            value = lunchTime, onValueChange = { lunchTime = it }
//        )
//        Spacer(modifier = Modifier.height(20.dp))
        Text(stringResource(Res.string.label_break_interval))
        Row(verticalAlignment = Alignment.CenterVertically) {
            NumberPicker(
                value = breakInterval.toInt(),
                onValueChange = { breakInterval = it.toString() },
                (5..120 step 5).toList(),
                isEditable = false
            )
            Text(stringResource(Res.string.label_minutes))
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                saveData(
                    Config(
                        startWork = startWork,
                        endWork = endWork,
                        lunchTime = lunchTime,
                        breakInterval = breakInterval,
                        selectedDays = selectedDays
                    )
                )
            }
        ) {
            Text(stringResource(Res.string.btn_save_config))
        }
    }
}
