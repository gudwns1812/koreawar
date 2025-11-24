package dev.hjp.koreawargame.domain.domaindata

import dev.hjp.koreawargame.R

enum class Facilities(
    override val cost: Long,
    override val imageRes: Int = -1,
    override val displayName: String = "",
    val populationIncrease: Long,
    val economyPowerIncrease: Long,
    val approvalRateIncrease: Double,
    val limitRegionCount: Int = 0
) : GameItem {
    HOUSE(
        cost = 2_500L,
        populationIncrease = 200L,
        economyPowerIncrease = 1L,
        approvalRateIncrease = 0.01,
        imageRes = R.drawable.facility_house,
        displayName = "주거 시설 개발"
    ),
    COMMERCIAL(
        cost = 10_000L,
        populationIncrease = 900L,
        economyPowerIncrease = 5L,
        approvalRateIncrease = 0.05,
        limitRegionCount = 3,
        imageRes = R.drawable.facility_commercial,
        displayName = "상업 시설 개발"
    ),
    LEISURE(
        cost = 50_000L,
        populationIncrease = 4_600L,
        economyPowerIncrease = 27L,
        approvalRateIncrease = 0.2,
        limitRegionCount = 9,
        imageRes = R.drawable.facility_leisure,
        displayName = "여가 시설 개발"
    ),
    TRANSPORT(
        cost = 300_000L,
        populationIncrease = 28_000L,
        economyPowerIncrease = 165L,
        approvalRateIncrease = 0.5,
        limitRegionCount = 15,
        imageRes = R.drawable.facility_transport,
        displayName = "교통 시설 개발"
    ),
    INDUSTRY(
        cost = 2_000_000L,
        populationIncrease = 190_000L,
        economyPowerIncrease = 1_100L,
        approvalRateIncrease = 1.5,
        limitRegionCount = 24,
        imageRes = R.drawable.facility_industry,
        displayName = "산업 시설 개발"
    ),
    NEW_CITY(
        cost = 30_000_000L,
        populationIncrease = 2_900_000L,
        economyPowerIncrease = 17_000L,
        approvalRateIncrease = 3.5,
        limitRegionCount = 39,
        imageRes = R.drawable.facility_new_city,
        displayName = "신도시 개발"
    )
}