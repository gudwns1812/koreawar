package dev.hjp.koreawargame.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.hjp.koreawargame.presentation.ui.battle.BattleDetailScreen
import dev.hjp.koreawargame.presentation.ui.battle.MiddleBattleScreen
import dev.hjp.koreawargame.presentation.ui.battle.NorthBattleScreen
import dev.hjp.koreawargame.presentation.ui.battle.NorthKoreaBattleScreen
import dev.hjp.koreawargame.presentation.ui.battle.SouthBattleScreen
import dev.hjp.koreawargame.presentation.ui.game.FacilitiesScreen
import dev.hjp.koreawargame.presentation.ui.game.FactoryScreen
import dev.hjp.koreawargame.presentation.ui.game.MainScreen
import dev.hjp.koreawargame.presentation.ui.game.ResearchScreen
import dev.hjp.koreawargame.presentation.ui.tax.NorthKoreaTaxScreen
import dev.hjp.koreawargame.presentation.ui.tax.SouthKoreaTaxScreen
import dev.hjp.koreawargame.presentation.viewmodel.game.BattleViewModel
import dev.hjp.koreawargame.presentation.viewmodel.game.GameViewModel

@Composable
fun AppNavHost(
    gameViewModel: GameViewModel,
    battleViewModel: BattleViewModel
) {
    val navController = rememberNavController()

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
                onTaxClick = { navController.navigate("tax") },
                onBattleClick = { navController.navigate("southBattle") }
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

        composable("southBattle") {
            SouthBattleScreen(
                battleViewModel,
                gameViewModel,
                onBackClick = { navController.popBackStack() },
                middleRegionClick = { navController.navigate("middleBattle") },
                onBattleDetailClick = { navController.navigate("battleDetail") }
            )
        }

        composable("middleBattle") {
            MiddleBattleScreen(
                battleViewModel,
                gameViewModel,
                onBackClick = { navController.popBackStack() },
                northRegionClick = { navController.navigate("northBattle") },
                northKoreaClick = { navController.navigate("northKoreaBattle") },
                onBattleDetailClick = { navController.navigate("battleDetail") }
            )
        }

        composable("northBattle") {
            NorthBattleScreen(
                battleViewModel,
                gameViewModel,
                onBackClick = { navController.popBackStack() },
                onBattleDetailClick = { navController.navigate("battleDetail") }
            )
        }

        composable("northKoreaBattle") {
            NorthKoreaBattleScreen(
                battleViewModel,
                gameViewModel,
                onBackClick = { navController.popBackStack() },
                onBattleDetailClick = { navController.navigate("battleDetail") }
            )
        }

        composable("battleDetail") {
            BattleDetailScreen(
                battleViewModel,
                gameViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}