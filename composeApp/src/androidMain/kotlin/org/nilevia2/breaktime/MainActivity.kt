package org.nilevia2.breaktime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import data.ConfigRepository
import org.nilevia2.breaktime.data.getConfigDatabase
import ui.BreakTimeApp
import ui.viewmodel.ConfigViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val dao = getConfigDatabase(applicationContext).configDao()
            val repository = remember {
                ConfigRepository(dao)
            }
            val viewModel = remember {
                ConfigViewModel(repository)
            }

            BreakTimeApp(viewModel)
        }
    }
}
