package dev.hjp.koreawargame.data.repository.battle

import dev.hjp.koreawargame.domain.gamesave.BattleSaveData

interface BattleRepository {
    suspend fun save(data: BattleSaveData)

}