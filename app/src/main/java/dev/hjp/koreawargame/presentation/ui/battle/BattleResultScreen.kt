package dev.hjp.koreawargame.presentation.ui.battle

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.hjp.koreawargame.R
import dev.hjp.koreawargame.data.repository.battle.FakeBattleRepository
import dev.hjp.koreawargame.data.repository.game.FakeGameRepository
import dev.hjp.koreawargame.presentation.ui.common.Triangle
import dev.hjp.koreawargame.presentation.viewmodel.game.BattleViewModel
import dev.hjp.koreawargame.presentation.viewmodel.game.GameViewModel
import dev.hjp.koreawargame.ui.theme.ForestGreen

@Composable
fun BattleResultScreen(
    result: String,
    battleViewModel: BattleViewModel,
    gameViewModel: GameViewModel,
    navController: NavController = rememberNavController()
) {
    val isWin = result == "win"
    val battleCity = battleViewModel.currentTarget.value

    if (isWin && battleCity.regionName == "서울") {
        navController.navigate("gameClear") {
            popUpTo("result/${result}") {
                inclusive = true
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.backgroud_map),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = "-전투결과-",
            modifier = Modifier
                .zIndex(1f)
                .padding(15.dp)
                .align(Alignment.TopCenter),
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold
        )

        val country = battleViewModel.selectedCountry.value ?: error("나라가 없습니다.")
        val color = country.color

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.size(100.dp))

            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(300.dp, 200.dp)
                    .background(color = color)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.TopCenter),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = battleCity.regionName,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.size(30.dp))

                    Text(
                        text = if (isWin) "패" else "승",
                        fontSize = 50.sp,
                        color = if (isWin) Color.Blue else Color.Red,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }

            Spacer(Modifier.size(50.dp))

            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(300.dp, 200.dp)
                    .background(color = ForestGreen)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.TopCenter),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "아군",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.size(30.dp))

                    Text(
                        text = if (isWin) "승" else "패",
                        fontSize = 50.sp,
                        color = if (isWin) Color.Red else Color.Blue,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
            if (isWin) {
                gameViewModel.winSettle(country)
                battleViewModel.clear()
            }
            val nextCity = battleViewModel.currentTarget.value

            Text(
                text = if (isWin) """
                    ${battleCity.regionName} 점령에 성공하셨습니다!
                    
                    지역 수 + 1
                    인구 + ${country.clearIncreasePopulation}
                    지지도 - ${country.clearDecreaseApprovalRate}%
                    
                    다음 목표 - ${nextCity.regionName}
                """.trimIndent()
                else "${battleCity.regionName}점령에 실패했습니다.",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )

        }
        Triangle(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(5.dp),
            sizeDp = 50.dp,
            description = "메인 화면",
        ) {
            navController.navigate("main") {
                popUpTo("result/${result}") {
                    inclusive = true
                }
            }
        }
    }
}

@Preview
@Composable
fun BattleResultPreview() {
    BattleResultScreen(
        "win",
        battleViewModel = BattleViewModel(FakeBattleRepository()),
        gameViewModel = GameViewModel(FakeGameRepository())
    )
}