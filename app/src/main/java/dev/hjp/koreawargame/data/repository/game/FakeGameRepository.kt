package dev.hjp.koreawargame.data.repository.game

import dev.hjp.koreawargame.domain.gamesave.StateSaveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeGameRepository() : GameRepository {
    override suspend fun save(data: StateSaveData) {

    }

    override val savedStateData: Flow<StateSaveData> = MutableStateFlow(
        StateSaveData()
    )
}