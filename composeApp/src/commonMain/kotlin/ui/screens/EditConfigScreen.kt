package ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextLayoutInput
import model.Config
import ui.viewmodel.ConfigViewModel

@Composable
fun EditConfigScreen(viewModel: ConfigViewModel, configId: String? = null) {


    if (configId.isNullOrEmpty()){

        Column(modifier = Modifier.fillMaxSize()) {
            TextField(
                value =
            )
        }
    } else {

    }
}