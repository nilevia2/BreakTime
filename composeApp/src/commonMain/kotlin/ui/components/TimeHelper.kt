package ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

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
fun MultiDayPicker(initiallySelectedDays: Set<Int>, onDaysSelected: (Set<Int>) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val selectedDays = remember { mutableStateOf(initiallySelectedDays.toMutableSet()) }

    Box {
        Button(onClick = { expanded = true }) {
            Text("Selected: ${selectedDays.value.size}")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DayOfWeek.entries.forEach { day ->
                val isSelected = selectedDays.value.contains(day.value)
                DropdownMenuItem(onClick = {
                    if (isSelected) {
                        selectedDays.value.remove(day.value)
                    } else {
                        selectedDays.value.add(day.value)
                    }
                    onDaysSelected(selectedDays.value)
                }) {
                    Row {
                        Checkbox(
                            checked = isSelected,
                            onCheckedChange = null
                        )
                        Text(day.name)
                    }
                }
            }
        }
    }
}