package dev.hjp.koreawargame.data.repository.game

import dev.hjp.koreawargame.domain.gamesave.StateSaveData

class FakeGameRepository() : GameRepository {
    override suspend fun save(data: StateSaveData) {

    }
}