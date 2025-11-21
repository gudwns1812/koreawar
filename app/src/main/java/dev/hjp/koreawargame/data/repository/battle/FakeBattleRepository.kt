package dev.hjp.koreawargame.data.repository.battle

import dev.hjp.koreawargame.domain.gamesave.BattleSaveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeBattleRepository() : BattleRepository {
    override suspend fun save(data: BattleSaveData) {

    }

    override val savedBattleData: Flow<BattleSaveData> = MutableStateFlow(
        BattleSaveData()
    )
}