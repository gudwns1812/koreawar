package dev.hjp.koreawargame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.hjp.koreawargame.data.repository.BattleRepository
import dev.hjp.koreawargame.data.repository.TaxRepository
import dev.hjp.koreawargame.presentation.ui.navigation.AppNavHost
import dev.hjp.koreawargame.presentation.viewmodel.game.BattleViewModel
import dev.hjp.koreawargame.presentation.viewmodel.game.GameViewModel

class MainActivity : ComponentActivity() {

    val taxRepository = TaxRepository()
    val battleRepository = BattleRepository()
    val gameViewModel = GameViewModel(taxRepository = taxRepository)
    val battleViewModel = BattleViewModel(battleRepository, taxRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavHost(gameViewModel, battleViewModel)
        }
    }
}
