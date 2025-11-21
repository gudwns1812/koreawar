package dev.hjp.koreawargame.domain

import TaxProvince

data class GoldState(
    val gold: Long = 1_000L,
)

data class EconomyState(
    val approvalRate: Double = 60.0,
    val population: Long = 10_000L,
    val economyPower: Long = 0L,
)

data class RegionCountState(
    val regionCount: Int = 2
)

data class ResearchState(
    val researchPoints: Long = 0L
)

data class ArmyState(
    val militaryPower: Long = 100_000L,
    val fightPower: Double = militaryPower / 10.0
)

data class ProvinceState(
    val province: TaxProvince,
    val hasRegion: Boolean = false
)