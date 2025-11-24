package dev.hjp.koreawargame.domain.domaindata.war

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import dev.hjp.koreawargame.presentation.ui.battle.BattleBox
import dev.hjp.koreawargame.presentation.ui.battle.BattleStar
import dev.hjp.koreawargame.presentation.ui.battle.ClearBox
import dev.hjp.koreawargame.presentation.ui.common.safeClickable
import dev.hjp.koreawargame.presentation.ui.theme.OverlayDark
import dev.hjp.koreawargame.presentation.viewmodel.game.BattleViewModel

data class CityPosition(
    val countryName: String,
    val regionName: String,
    val x: Float,
    val y: Float
)

@Composable
fun ShowCities(
    cityPositions: List<CityPosition>,
    battleViewModel: BattleViewModel,
    width: Dp,
    height: Dp,
    onBattleDetailClick: () -> Unit,
    fixedTextColor: Color? = null
) {
    cityPositions.forEach { pos ->
        val country = battleViewModel.findCountry(pos.countryName)
        val city = country.cities.find { it.regionName == pos.regionName } ?: return@forEach
        val currentTarget = battleViewModel.currentTarget.collectAsState().value

        val isCurrent = city == currentTarget

        if (pos.regionName == "서울") {
            BattleStar(
                text = "서울",
                modifier = Modifier
                    .size(80.dp)
                    .offset(width.times(pos.x), height.times(pos.y))
                    .safeClickable { if (isCurrent) onBattleDetailClick() },
                starColor = if (isCurrent) Color.Red else OverlayDark
            )
            return
        }

        if (city.isClear) {
            ClearBox(
                modifier = Modifier
                    .zIndex(1f)
                    .offset(width.times(pos.x), height.times(pos.y)),
            )
        } else {
            BattleBox(
                modifier = Modifier
                    .zIndex(1f)
                    .offset(width.times(pos.x), height.times(pos.y))
                    .background(if (isCurrent) country.color else OverlayDark),
                city = city,
                fixedTextColor = fixedTextColor,
                onClick = {
                    if (isCurrent) {
                        battleViewModel.selectCountry(country)
                        onBattleDetailClick()
                    }
                }
            )
        }

    }
}

val southCityPositions = listOf(
    CityPosition("전라", "해남", 0.22f, 0.77f),
    CityPosition("전라", "여수", 0.37f, 0.67f),
    CityPosition("전라", "목포", 0.21f, 0.65f),

    CityPosition("광주", "남원", 0.365f, 0.54f),
    CityPosition("광주", "전주", 0.28f, 0.42f),
    CityPosition("광주", "광주", 0.22f, 0.52f),

    CityPosition("부산", "진주", 0.5f, 0.63f),
    CityPosition("부산", "합천", 0.51f, 0.484f),
    CityPosition("부산", "울산", 0.66f, 0.49f),
    CityPosition("부산", "부산", 0.63f, 0.58f),

    CityPosition("충청", "공주", 0.275f, 0.3f),
    CityPosition("충청", "서산", 0.25f, 0.2f),

    CityPosition("경상", "포항", 0.71f, 0.38f),
    CityPosition("경상", "울진", 0.72f, 0.19f),
    CityPosition("경상", "안동", 0.59f, 0.22f),
    CityPosition("경상", "김천", 0.51f, 0.3f),
    CityPosition("경상", "대구", 0.57f, 0.39f),

    CityPosition("대전", "무주", 0.43f, 0.38f),
    CityPosition("대전", "대전", 0.4f, 0.26f),
    CityPosition("대전", "청주", 0.4f, 0.15f),
    CityPosition("대전", "원주", 0.535f, 0.13f)
)

val middleCityPositions = listOf(
    CityPosition("강원", "평창", 0.57f, 0.73f),
    CityPosition("강원", "강릉", 0.72f, 0.75f),
    CityPosition("강원", "인제", 0.6f, 0.63f),
    CityPosition("강원", "속초", 0.7f, 0.55f),
    CityPosition("강원", "춘천", 0.52f, 0.56f),

    CityPosition("원산", "고성", 0.54f, 0.48f),
    CityPosition("원산", "철원", 0.42f, 0.4f),
    CityPosition("원산", "통천", 0.56f, 0.36f),
    CityPosition("원산", "신평", 0.42f, 0.3f),
    CityPosition("원산", "원산", 0.46f, 0.15f),

    CityPosition("평양", "곡산", 0.31f, 0.25f),
    CityPosition("평양", "순천", 0.33f, 0.08f),
    CityPosition("평양", "구성", 0.18f, 0.08f),
    CityPosition("평양", "남포", 0.17f, 0.26f),
    CityPosition("평양", "평양", 0.23f, 0.14f),
)

val northCityPositions = listOf(
    CityPosition("평안", "운산", 0.25f, 0.73f),
    CityPosition("평안", "초산", 0.18f, 0.64f),
    CityPosition("평안", "만포", 0.22f, 0.55f),
    CityPosition("평안", "벽동", 0.08f, 0.58f),
    CityPosition("평안", "신의주", 0.06f, 0.7f),

    CityPosition("함흥", "맹산", 0.4f, 0.76f),
    CityPosition("함흥", "장진", 0.42f, 0.595f),
    CityPosition("함흥", "강계", 0.32f, 0.65f),
    CityPosition("함흥", "낭림", 0.35f, 0.49f),
    CityPosition("함흥", "자성", 0.34f, 0.38f),
    CityPosition("함흥", "갑산", 0.49f, 0.52f),
    CityPosition("함흥", "함흥", 0.53f, 0.65f),

    CityPosition("함경", "단천", 0.64f, 0.54f),
    CityPosition("함경", "혜산", 0.51f, 0.42f),
    CityPosition("함경", "백암", 0.6f, 0.36f),
    CityPosition("함경", "삼지연", 0.56f, 0.25f),
    CityPosition("함경", "나선", 0.75f, 0.12f),
    CityPosition("함경", "청진", 0.74f, 0.43f),
)


val northKoreaCityPositions = listOf(
    CityPosition("북한", "해주", 0.12f, 0.32f),
    CityPosition("북한", "사리원", 0.3f, 0.22f),
    CityPosition("북한", "연천", 0.55f, 0.25f),
    CityPosition("북한", "개성", 0.45f, 0.4f),
    CityPosition("북한", "이천", 0.88f, 0.55f),
    CityPosition("북한", "용인", 0.84f, 0.8f),
    CityPosition("북한", "수원", 0.68f, 0.8f),
    CityPosition("북한", "인천", 0.6f, 0.55f),
    CityPosition("북한", "서울", 0.7f, 0.62f),
)
