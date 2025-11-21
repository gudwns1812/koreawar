package dev.hjp.koreawargame.data.repository.battle

import dev.hjp.koreawargame.domain.gamesave.BattleSaveData

class FakeBattleRepository() : BattleRepository {
    override suspend fun save(data: BattleSaveData) {

    }
}