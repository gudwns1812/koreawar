package dev.hjp.koreawargame.domain.gamesave

import NorthKoreaTaxProvince
import SouthKoreaTaxProvince
import SouthKoreaTaxProvince.JEJU
import dev.hjp.koreawargame.domain.ArmyState
import dev.hjp.koreawargame.domain.EconomyState
import dev.hjp.koreawargame.domain.GoldState
import dev.hjp.koreawargame.domain.ProvinceState
import dev.hjp.koreawargame.domain.ResearchState
import dev.hjp.koreawargame.domain.domaindata.war.Country
import dev.hjp.koreawargame.domain.domaindata.war.initialCountries
import kotlinx.serialization.Serializable

@Serializable
data class GameSaveData(
    val gold: GoldState = GoldState(),
    val economy: EconomyState = EconomyState(),
    val research: ResearchState = ResearchState(),
    val army: ArmyState = ArmyState(),
    val southProvince: List<ProvinceState> = emptyList(),
    val northProvince: List<ProvinceState> = emptyList(),

    val countries: List<Country> = initialCountries,
    val currentTargetId: Int = 0,
)

data class StateSaveData(
    val gold: GoldState = GoldState(),
    val economy: EconomyState = EconomyState(),
    val research: ResearchState = ResearchState(),
    val army: ArmyState = ArmyState(),
    val southProvince: List<ProvinceState> = SouthKoreaTaxProvince.entries.map { province ->
        if (province == JEJU) ProvinceState(province, true)
        else ProvinceState(province)
    },
    val northProvince: List<ProvinceState> = NorthKoreaTaxProvince.entries.map { province ->
        ProvinceState(province)
    },
)

data class BattleSaveData(
    val countries: List<Country> = initialCountries,
    val currentTargetId: Int = 1,
)
