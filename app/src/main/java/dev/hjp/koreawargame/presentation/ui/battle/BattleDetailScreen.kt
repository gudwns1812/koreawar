package dev.hjp.koreawargame.presentation.ui.battle

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import dev.hjp.koreawargame.R
import dev.hjp.koreawargame.data.repository.BattleRepository
import dev.hjp.koreawargame.data.repository.TaxRepository
import dev.hjp.koreawargame.presentation.ui.common.Triangle
import dev.hjp.koreawargame.presentation.ui.common.safeClickable
import dev.hjp.koreawargame.presentation.viewmodel.game.BattleViewModel
import dev.hjp.koreawargame.presentation.viewmodel.game.GameViewModel
import dev.hjp.koreawargame.ui.theme.ForestGreen

@Composable
fun BattleDetailScreen(
    battleViewModel: BattleViewModel,
    gameViewModel: GameViewModel,
    onBackClick: () -> Unit = {},
    onBattleClick: () -> Unit = {}
) {
    val selectedCity = battleViewModel.selectedCity.value
    val color = battleViewModel.selectedColor.value

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(100.dp))

            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(300.dp, 200.dp)
                    .background(color = color ?: Color.Yellow)
            ) {
                val militaryPower = selectedCity?.militaryPower ?: 200

                Column(
                    modifier = Modifier.align(Alignment.TopCenter),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = selectedCity?.regionName ?: "해남",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(
                        text = "적군 군사력 : $militaryPower",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Text(
                text = "전투 개시",
                fontSize = 30.sp,
                modifier = Modifier
                    .safeClickable {
                        gameViewModel.battle()
                        onBattleClick()
                    },
                color = Color.Red,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )

            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(300.dp, 200.dp)
                    .background(color = ForestGreen)
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.TopCenter),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val userMilitary = gameViewModel.army.value

                    Text(
                        text = "아군",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(
                        text = "아군 군사력 : ${userMilitary.militaryPower}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.size(50.dp))

            Text(
                text = """
                    주의 - 전투를 개시하면
                    지지도가 0.5% 감소하고,
                    인구가 100 감소합니다.
                """.trimIndent(),
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Triangle(
            modifier = Modifier
                .align(Alignment.BottomStart),
            sizeDp = 50.dp,
            description = "뒤로가기",
        ) { onBackClick() }
    }
}

@Preview
@Composable
fun BattleDetailScreenPreview() {
    BattleDetailScreen(
        battleViewModel = BattleViewModel(
            battleRepository = BattleRepository(),
            taxRepository = TaxRepository()
        ),
        gameViewModel = GameViewModel(
            TaxRepository()
        )
    )
}
