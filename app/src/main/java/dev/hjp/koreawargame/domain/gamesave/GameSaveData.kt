package dev.hjp.koreawargame.domain.gamesave

import dev.hjp.koreawargame.domain.ArmyState
import dev.hjp.koreawargame.domain.EconomyState
import dev.hjp.koreawargame.domain.GoldState
import dev.hjp.koreawargame.domain.ProvinceState
import dev.hjp.koreawargame.domain.RegionCountState
import dev.hjp.koreawargame.domain.ResearchState
import dev.hjp.koreawargame.domain.domaindata.war.Country
import kotlinx.serialization.Serializable

@Serializable
data class GameSaveData(
    val gold: GoldState = GoldState(),
    val economy: EconomyState = EconomyState(),
    val regionCountState: RegionCountState = RegionCountState(),
    val research: ResearchState = ResearchState(),
    val army: ArmyState = ArmyState(),
    val southProvince: List<ProvinceState> = emptyList(),
    val northProvince: List<ProvinceState> = emptyList(),

    val countries: List<Country> = emptyList(),
    val currentTargetId: Int = 0,
)

data class StateSaveData(
    val gold: GoldState = GoldState(),
    val economy: EconomyState = EconomyState(),
    val regionCountState: RegionCountState = RegionCountState(),
    val research: ResearchState = ResearchState(),
    val army: ArmyState = ArmyState()
)

data class TaxSaveData(
    val southProvince: List<ProvinceState> = emptyList(),
    val northProvince: List<ProvinceState> = emptyList(),
)

data class BattleSaveData(
    val countries: List<Country> = emptyList(),
    val currentTargetId: Int = 0,
)
