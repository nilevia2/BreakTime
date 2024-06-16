package ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import breaktime.composeapp.generated.resources.Res
import breaktime.composeapp.generated.resources.btn_change_config
import breaktime.composeapp.generated.resources.label_break_interval
import breaktime.composeapp.generated.resources.label_end_work
import breaktime.composeapp.generated.resources.label_lunch_time
import breaktime.composeapp.generated.resources.label_start_work
import breaktime.composeapp.generated.resources.quote
import model.Config
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.components.DisplayText
import ui.viewmodel.ConfigViewModel
import kotlin.random.Random

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun HomePage(viewModel: ConfigViewModel){
    val config = viewModel.configurationState.collectAsState()
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(Res.string.quote),
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(36.dp))

            if (config.value == null){
                Button(
                    onClick = {
                        viewModel.saveConfig(
                            Config(
                                startWork = "9:00",
                                endWork = "17:00",
                                lunchTime = "12:00",
                                breakInterval = "20"
                            )
                        )
                    }
                ){
                    Text("Insert Initial Data")
                }
            } else ShowData(config.value!!){
                viewModel.saveConfig(
                    Config(
                        Random.nextInt().toString(),
                        Random.nextInt().toString(),
                        Random.nextInt().toString(),
                        Random.nextInt().toString()
                    )
                )
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ShowData(config: Config, onEditClick: () -> Unit){
    DisplayText(stringResource(Res.string.label_start_work), config.startWork)
    DisplayText(stringResource(Res.string.label_end_work), config.endWork)
    DisplayText(stringResource(Res.string.label_lunch_time), config.lunchTime)
    DisplayText(stringResource(Res.string.label_break_interval), config.breakInterval)
    Spacer(modifier = Modifier.height(42.dp))
    Button(
        onClick = {
            onEditClick.invoke()
        }
    ){
        Text(stringResource(Res.string.btn_change_config))
    }
}

//@Composable
//fun EditData(onSave: () -> Unit){
//
//}