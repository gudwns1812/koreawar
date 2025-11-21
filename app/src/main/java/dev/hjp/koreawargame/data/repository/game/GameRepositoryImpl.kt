package dev.hjp.koreawargame.data.repository.game

import androidx.datastore.core.DataStore
import dev.hjp.koreawargame.domain.gamesave.GameSaveData
import dev.hjp.koreawargame.domain.gamesave.StateSaveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepositoryImpl(
    private val dataStore: DataStore<GameSaveData>
) : GameRepository {
    override suspend fun save(data: StateSaveData) {
        dataStore.updateData {
            it.copy(
                gold = data.gold,
                economy = data.economy,
                regionCountState = data.regionCountState,
                research = data.research,
                army = data.army,
            )
        }
    }

    val savedStateData: Flow<StateSaveData> = dataStore.data.map {
        StateSaveData(
            it.gold,
            it.economy,
            it.regionCountState,
            it.research,
            it.army
        )
    }

}