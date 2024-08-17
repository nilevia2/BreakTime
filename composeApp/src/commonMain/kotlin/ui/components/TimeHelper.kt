package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Calendar
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import breaktime.composeapp.generated.resources.Res
import breaktime.composeapp.generated.resources.label_minutes
import breaktime.composeapp.generated.resources.label_hour
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.plus
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

enum class DayOfWeek(val value: Int){
    SUNDAY(1),
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6),
    SATURDAY(7)
}

fun getStringOfDay(day: Int): String {
    return when (day) {
        DayOfWeek.SUNDAY.value -> "Sunday"
        DayOfWeek.MONDAY.value -> "Monday"
        DayOfWeek.TUESDAY.value -> "Tuesday"
        DayOfWeek.WEDNESDAY.value -> "Wednesday"
        DayOfWeek.THURSDAY.value -> "Thursday"
        DayOfWeek.FRIDAY.value -> "Friday"
        DayOfWeek.SATURDAY.value -> "Saturday"
        else -> ""
    }
}
@Composable
fun DayCircle(
    dayOfWeek: DayOfWeek,
    isSelected: Boolean,
    isEditable: Boolean = false,
    onDayClick: (DayOfWeek) -> Unit = {}
) {
    val color = if (isSelected) Color.Blue else Color.LightGray
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(color)
            .clickable(enabled = isEditable) { onDayClick(dayOfWeek) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = dayOfWeek.name.first().toString(),
            color = Color.White
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TimePicker(
    initialTime: String,
    minHour: Int = 0,
    minMinute: Int = 0,
    onTimeSelected: (Int, Int) -> Unit
) {
    val (hour, minute) = initialTime.split(":").map { it.toIntOrNull() ?: 0 }
    var selectedHour by remember { mutableStateOf(hour) }
    var selectedMinute by remember { mutableStateOf(minute) }

    Column {
        Spacer(modifier = Modifier.height(6.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            NumberPicker(
                value = selectedHour,
                onValueChange = { selectedHour = it },
                (minHour..23).toList()
            )
            Text(stringResource(Res.string.label_hour))
            Spacer(modifier = Modifier.width(16.dp))
            NumberPicker(
                value = selectedMinute,
                onValueChange = { selectedMinute = it },
                (minMinute..45 step 15).toList()
            )
            Text(stringResource(Res.string.label_minutes))
        }

        onTimeSelected(selectedHour, selectedMinute)
    }
}

@Composable
fun NumberPicker(
    value: Int,
    onValueChange: (Int) -> Unit,
    list: List<Int>,
    isEditable: Boolean = false
) {
    var currentValue by remember { mutableStateOf(value) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(onClick = {
            val currentIndex = list.indexOf(currentValue)
            currentValue = list[(currentIndex + 1).mod(list.size)]
            onValueChange(currentValue)
        }) {
            Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Increase")
        }
        if (isEditable){
            OutlinedTextField(
                modifier = Modifier.width(42.dp),
                value = currentValue.toString(),
                onValueChange = {
                    val newValue = it.toIntOrNull() ?: list.first()
                    currentValue = newValue
                    onValueChange(currentValue)
                }
            )
        } else {
                Text(
                    text = currentValue.toString().padStart(2, '0'),
                    textAlign = TextAlign.Center,
                )
        }

        IconButton(onClick = {
            val currentIndex = list.indexOf(currentValue)
            currentValue = list[(currentIndex - 1).mod(list.size)]
            onValueChange(currentValue)
        }) {
            Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Decrease")
        }
    }
}
