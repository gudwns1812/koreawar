package dev.hjp.koreawargame.domain.domaindata

import dev.hjp.koreawargame.R

enum class UnitType(
    override val cost: Long,
    override val imageRes: Int = -1,
    override val displayName: String = "",
    val attackPower: Long,
    val researchLimit: Int
) : GameItem {
    INFANTRY(
        cost = 500L,
        attackPower = 1L,
        researchLimit = 0,
        imageRes = R.drawable.unittype_infantry,
        displayName = "보병 생산"
    ),
    GUNNER(
        cost = 2_000L,
        attackPower = 6L,
        researchLimit = 20,
        imageRes = R.drawable.unittype_gunner,
        displayName = "총 생산"
    ),
    GRENADE(
        cost = 6_000L,
        attackPower = 25L,
        researchLimit = 175,
        imageRes = R.drawable.unittype_grenade,
        displayName = "수류탄 생산"
    ),
    MORTAR(
        cost = 50_000L,
        attackPower = 250L,
        researchLimit = 2200,
        imageRes = R.drawable.unittype_mortar,
        displayName = "박격포 생산"
    ),
    TANK(
        cost = 500_000L,
        attackPower = 2600L,
        researchLimit = 15000,
        imageRes = R.drawable.unittype_tank,
        displayName = "탱크 생산"
    ),
    JET(
        cost = 8_000_000L,
        attackPower = 43_000L,
        researchLimit = 300_000,
        imageRes = R.drawable.unittype_jet,
        displayName = "전투기 생산"
    ),
    NUCLEAR_MISSILE(
        cost = 150_000_000L,
        attackPower = 800_000L,
        researchLimit = 2_500_000,
        imageRes = R.drawable.unittype_nuclear_missile,
        displayName = "핵미사일 생산"
    )

}