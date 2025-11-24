package dev.hjp.koreawargame.domain

import TaxProvince
import kotlinx.serialization.Serializable

@Serializable
data class GoldState(
    val gold: Long = 1_000L,
)

@Serializable
data class EconomyState(
    val approvalRate: Double = 60.0,
    val population: Long = 10_000L,
    val economyPower: Long = 0L,
    val regionCount: Int = 2

)

@Serializable
data class ResearchState(
    val researchPoints: Long = 0L
)

@Serializable
data class ArmyState(
    val militaryPower: Long = 100L
) {
    val fightPower: Double
        get() = militaryPower / 10.0
}

@Serializable
data class ProvinceState(
    val province: TaxProvince,
    val hasRegion: Boolean = false
)