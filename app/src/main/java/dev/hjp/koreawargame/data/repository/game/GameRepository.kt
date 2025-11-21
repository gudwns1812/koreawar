package dev.hjp.koreawargame.data.repository.game

import dev.hjp.koreawargame.domain.gamesave.StateSaveData
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    suspend fun save(data: StateSaveData)

    val savedStateData: Flow<StateSaveData>
}