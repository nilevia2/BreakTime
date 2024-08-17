package ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ui.viewmodel.ConfigViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ui.screens.EditConfigScreen
import ui.screens.HomePageScreen

enum class BreakTimeScreen(val title: String) {
    Homepage("Break Time"),
    AddConfig("Add Config"),
    EditConfig("Edit Config")
}


@Composable
fun BreakTimeAppBar(
    currentScreen: BreakTimeScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(currentScreen.title) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            }
        }
    )
}

@Composable
fun BreakTimeApp(
    viewModel: ConfigViewModel,
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = BreakTimeScreen.valueOf(
        backStackEntry?.destination?.route ?: BreakTimeScreen.Homepage.name
    )

    Scaffold(
        topBar = {
            BreakTimeAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = BreakTimeScreen.Homepage.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = BreakTimeScreen.Homepage.name) {
                HomePageScreen(viewModel, navController)
            }

            composable(route = BreakTimeScreen.AddConfig.name) {
                EditConfigScreen(viewModel, navController)
            }

            composable(
                // hopefully they update to the new one soon
                route = BreakTimeScreen.EditConfig.name+"/{configId}",
                arguments = listOf(
                    navArgument("configId"){
                        type = NavType.StringType
                    }
                )
            ) {
                val configId = backStackEntry?.arguments?.getString("configId")
                EditConfigScreen(viewModel,navController ,configId )
            }
        }
    }
}