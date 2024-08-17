/*
package org.nilevia2.breaktime.ui

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
import ui.components.DayOfWeek
import ui.components.MultiDayPicker

class MultiDayPickerAndroid: MultiDayPicker {
    @Composable
    override fun show(initiallySelectedDays: Set<DayOfWeek>, onDaysSelected: (Set<DayOfWeek>) -> Unit) {
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
                DayOfWeek.values().forEach { day ->
                    val isSelected = selectedDays.value.contains(day)
                    DropdownMenuItem(onClick = {
                        if (isSelected) {
                            selectedDays.value.remove(day)
                        } else {
                            selectedDays.value.add(day)
                        }
                        onDaysSelected(selectedDays.value)
                    }) {
                        Row {
                            Checkbox(
                                checked = isSelected,
                                onCheckedChange = null // Handled by parent click
                            )
                            Text(day.name)
                        }
                    }
                }
            }
        }
    }
}*/
