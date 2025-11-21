package dev.hjp.koreawargame.data.repository.game

import dev.hjp.koreawargame.domain.gamesave.StateSaveData

interface GameRepository {
    suspend fun save(data: StateSaveData)
}