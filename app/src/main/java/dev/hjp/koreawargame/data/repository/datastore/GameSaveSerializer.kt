package dev.hjp.koreawargame.data.repository.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import dev.hjp.koreawargame.domain.gamesave.GameSaveData
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object GameSaveSerializer : Serializer<GameSaveData> {
    private val json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    override val defaultValue: GameSaveData = GameSaveData()
    override suspend fun readFrom(input: InputStream): GameSaveData {
        return try {
            val text = input.readBytes().decodeToString()
            if (text.isBlank()) defaultValue
            json.decodeFromString(GameSaveData.serializer(), text)
        } catch (e: Exception) {
            throw CorruptionException("Cannot read save data", e)
        }
    }

    override suspend fun writeTo(
        t: GameSaveData,
        output: OutputStream
    ) {
        val text = json.encodeToString(GameSaveData.serializer(), t)
        output.write(text.encodeToByteArray())
    }
}