package dev.hjp.koreawargame.domain.domaindata.war

import kotlinx.serialization.Serializable

@Serializable
data class BattleCity(
    val id: Int,
    val regionName: String,
    val militaryPower: Long,
    val isCapital: Boolean = false,
    val isClear: Boolean = false,
    val fightPower: Double = militaryPower / 10.0,
)