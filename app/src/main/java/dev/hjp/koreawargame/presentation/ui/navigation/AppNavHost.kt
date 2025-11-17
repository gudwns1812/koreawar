package dev.hjp.koreawargame.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.hjp.koreawargame.data.repository.TaxRepository
import dev.hjp.koreawargame.presentation.ui.game.FacilitiesScreen
import dev.hjp.koreawargame.presentation.ui.game.FactoryScreen
import dev.hjp.koreawargame.presentation.ui.game.MainScreen
import dev.hjp.koreawargame.presentation.ui.game.ResearchScreen
import dev.hjp.koreawargame.presentation.ui.tax.NorthKoreaTaxScreen
import dev.hjp.koreawargame.presentation.ui.tax.SouthKoreaTaxScreen
import dev.hjp.koreawargame.presentation.viewmodel.game.GameViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val gameViewModel = GameViewModel(TaxRepository())

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(
                viewModel = gameViewModel,
                onFactoryClick = { navController.navigate("factory") },
                onFacilityClick = { navController.navigate("facilities") },
                onResearchClick = { navController.navigate("research") },
                onTaxClick = { navController.navigate("tax") }
            )
        }

        composable("factory") {
            FactoryScreen(
                viewModel = gameViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("facilities") {
            FacilitiesScreen(
                viewModel = gameViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("research") {
            ResearchScreen(
                viewModel = gameViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("tax") {
            SouthKoreaTaxScreen(
                viewModel = gameViewModel,
                onBackClick = { navController.popBackStack() },
                onNextPage = { navController.navigate("northKoreaTax") }
            )
        }

        composable("northKoreaTax") {
            NorthKoreaTaxScreen(
                viewModel = gameViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}