package ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import breaktime.composeapp.generated.resources.Res
import breaktime.composeapp.generated.resources.btn_add_config
import breaktime.composeapp.generated.resources.btn_change_config
import breaktime.composeapp.generated.resources.label_break_interval
import breaktime.composeapp.generated.resources.label_end_work
import breaktime.composeapp.generated.resources.label_lunch_time
import breaktime.composeapp.generated.resources.label_selected_days
import breaktime.composeapp.generated.resources.label_start_work
import breaktime.composeapp.generated.resources.quote
import model.Config
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.BreakTimeScreen
import ui.components.DayCircle
import ui.components.DayOfWeek
import ui.components.DisplayText
import ui.components.getStringOfDay
import ui.viewmodel.ConfigViewModel

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun HomePageScreen(viewModel: ConfigViewModel, navController: NavHostController){
    val config = viewModel.configurationState.collectAsState()
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
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
                ShowNoData {
                    navController.navigate(BreakTimeScreen.AddConfig.name)
                }
            } else
                ShowData(config.value!!){
                    navController.navigate(BreakTimeScreen.AddConfig.name)
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ShowData(config: Config, onEditClick: () -> Unit){
    Column {

        Text(
            fontSize = 12.sp,
            fontStyle = FontStyle.Italic,
            text = stringResource(Res.string.label_selected_days),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            DayOfWeek.entries.forEach { day ->
                DayCircle(
                    dayOfWeek = day,
                    isSelected = config.selectedDays.contains(day.value)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        DisplayText(stringResource(Res.string.label_start_work), config.startWork)
        DisplayText(stringResource(Res.string.label_end_work), config.endWork)
        DisplayText(stringResource(Res.string.label_break_interval), config.breakInterval)
        Spacer(modifier = Modifier.height(42.dp))
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                onEditClick.invoke()
            }
        ){
            Text(stringResource(Res.string.btn_change_config))
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ShowNoData(onAddClick:() -> Unit){
    Column(modifier = Modifier.fillMaxWidth()) {
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                onAddClick.invoke()
            }
        ){
            Text(stringResource(Res.string.btn_add_config))
        }
    }
}