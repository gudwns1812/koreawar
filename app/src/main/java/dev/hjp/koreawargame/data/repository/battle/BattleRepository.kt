package dev.hjp.koreawargame.data.repository.battle

import dev.hjp.koreawargame.domain.gamesave.BattleSaveData
import kotlinx.coroutines.flow.Flow

interface BattleRepository {
    suspend fun save(data: BattleSaveData)
    val savedBattleData: Flow<BattleSaveData>
}