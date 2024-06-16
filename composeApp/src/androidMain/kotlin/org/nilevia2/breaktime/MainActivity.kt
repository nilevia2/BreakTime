package org.nilevia2.breaktime

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import data.ConfigRepository
import org.nilevia2.breaktime.data.getConfigDatabase
import ui.screen.HomePage
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

            HomePage(viewModel)
        }
    }
}
