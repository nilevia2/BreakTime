import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import data.ConfigRepository
import data.getConfigDatabase
import ui.BreakTimeApp
import ui.viewmodel.ConfigViewModel

//iOS App
// Translate compose into iOS code
fun MainViewController() = ComposeUIViewController {
    val dao = remember {
        getConfigDatabase().configDao()
    }
    val repository = remember {
        ConfigRepository(dao)
    }
    val viewModel = remember {
        ConfigViewModel(repository)
    }

    BreakTimeApp(viewModel)
}