package dev.hjp.koreawargame.data.repository.battle

import androidx.datastore.core.DataStore
import dev.hjp.koreawargame.domain.gamesave.BattleSaveData
import dev.hjp.koreawargame.domain.gamesave.GameSaveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BattleRepositoryImpl(
    private val dataStore: DataStore<GameSaveData>
) : BattleRepository {
    override suspend fun save(data: BattleSaveData) {
        dataStore.updateData { current ->
            current.copy(
                countries = data.countries,
                currentTargetId = data.currentTargetId
            )
        }
    }

    override val savedBattleData: Flow<BattleSaveData> = dataStore.data.map {
        BattleSaveData(it.countries, it.currentTargetId)
    }
}
