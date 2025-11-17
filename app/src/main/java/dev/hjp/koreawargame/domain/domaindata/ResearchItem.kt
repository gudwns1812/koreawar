package dev.hjp.koreawargame.domain.domaindata

import dev.hjp.koreawargame.R

enum class ResearchItem(
    override val cost: Long,
    override val imageRes: Int,
    override val displayName: String,
    val researchPointIncrease: Long,
    val limitEconomyPower: Int
) : GameItem {

    BASIC_RESEARCH(
        cost = 5_000L,
        researchPointIncrease = 1L,
        limitEconomyPower = 0,
        imageRes = R.drawable.basic_research,
        displayName = "기초 연구"
    ),
    BEGINNER_RESEARCH(
        cost = 30_000L,
        researchPointIncrease = 6L,
        limitEconomyPower = 40,
        imageRes = R.drawable.basic_research,
        displayName = "초급 연구"
    ),
    INTERMEDIATE_RESEARCH(
        cost = 90_000L,
        researchPointIncrease = 20L,
        limitEconomyPower = 300,
        imageRes = R.drawable.basic_research,
        displayName = "중급 연구"
    ),
    UPPER_INTERMEDIATE_RESEARCH(
        cost = 500_000L,
        researchPointIncrease = 115L,
        limitEconomyPower = 7_500,
        imageRes = R.drawable.basic_research,
        displayName = "중고급 연구"
    ),
    ADVANCED_RESEARCH(
        cost = 5_000_000L,
        researchPointIncrease = 1200L,
        limitEconomyPower = 70_000,
        imageRes = R.drawable.basic_research,
        displayName = "고급 연구"
    ),
    TOP_LEVEL_RESEARCH(
        cost = 80_000_000L,
        researchPointIncrease = 19_300L,
        limitEconomyPower = 800_000,
        imageRes = R.drawable.basic_research,
        displayName = "최고급 연구"
    )
}