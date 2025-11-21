package dev.hjp.koreawargame

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import dev.hjp.koreawargame.data.repository.battle.BattleRepositoryImpl
import dev.hjp.koreawargame.data.repository.datastore.GameSaveSerializer
import dev.hjp.koreawargame.data.repository.game.GameRepositoryImpl
import dev.hjp.koreawargame.domain.gamesave.GameSaveData
import dev.hjp.koreawargame.presentation.ui.navigation.AppNavHost
import dev.hjp.koreawargame.presentation.viewmodel.game.BattleViewModel
import dev.hjp.koreawargame.presentation.viewmodel.game.GameViewModel

private val Context.gameDataStore: DataStore<GameSaveData> by dataStore(
    fileName = "game_save_data.json",
    serializer = GameSaveSerializer
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val gameRepository = GameRepositoryImpl(applicationContext.gameDataStore)
        val battleRepository = BattleRepositoryImpl(applicationContext.gameDataStore)
        val gameViewModel = GameViewModel(gameRepository = gameRepository)
        val battleViewModel = BattleViewModel(battleRepository)

        setContent {
            AppNavHost(gameViewModel, battleViewModel)
        }
    }
}
