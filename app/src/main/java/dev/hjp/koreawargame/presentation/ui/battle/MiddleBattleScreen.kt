package dev.hjp.koreawargame.presentation.ui.battle

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.hjp.koreawargame.R
import dev.hjp.koreawargame.data.repository.TaxRepository
import dev.hjp.koreawargame.data.repository.battle.FakeBattleRepository
import dev.hjp.koreawargame.data.repository.game.FakeGameRepository
import dev.hjp.koreawargame.domain.domaindata.war.ShowCities
import dev.hjp.koreawargame.domain.domaindata.war.middleCityPositions
import dev.hjp.koreawargame.presentation.ui.common.GameLayout
import dev.hjp.koreawargame.presentation.ui.common.GameStatusPanel
import dev.hjp.koreawargame.presentation.ui.common.Triangle
import dev.hjp.koreawargame.presentation.ui.common.safeClickable
import dev.hjp.koreawargame.presentation.viewmodel.game.BattleViewModel
import dev.hjp.koreawargame.presentation.viewmodel.game.GameViewModel

@Composable
fun MiddleBattleScreen(
    battleViewModel: BattleViewModel,
    gameViewModel: GameViewModel,
    navController: NavController = rememberNavController()
) {
    GameLayout(
        content = {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
            ) {
                val boxWidth = maxWidth
                val boxHeight = maxHeight

                Image(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .size(width = boxWidth, height = boxHeight * 1.1f),
                    painter = painterResource(R.drawable.middl_battle_map),
                    contentDescription = "battle_map",
                    contentScale = ContentScale.FillBounds
                )

                MiddleBattleBoxContent(
                    boxWidth,
                    boxHeight,
                    battleViewModel
                ) { navController.navigate("battleDetail") }

                Box(
                    modifier = Modifier
                        .zIndex(2f)
                        .offset(boxWidth.times(0.2f), boxHeight.times(0.4f))
                        .border(2.dp, Color.Black)
                        .size(60.dp, 60.dp)
                        .safeClickable { navController.navigate("northKoreaBattle") }
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center),
                        text = "클릭",
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                Triangle(
                    modifier = Modifier
                        .offset(x = boxWidth.times(0.27f)),
                    sizeDp = 50.dp,
                    description = "북부 지역",
                    angle = 270f
                ) { navController.navigate("northBattle") }

                Triangle(
                    modifier = Modifier
                        .align(Alignment.BottomCenter),
                    sizeDp = 50.dp,
                    description = "남부 지역",
                    angle = 90f
                ) { navController.popBackStack() }
            }
        },
        bottomContent = {
            GameStatusPanel(
                gameViewModel,
                navController = navController
            )
        }
    )
}

@Composable
fun MiddleBattleBoxContent(
    width: Dp,
    height: Dp,
    battleViewModel: BattleViewModel,
    onBattleDetailClick: () -> Unit
) {
    val cityPositions = remember { middleCityPositions }

    ShowCities(cityPositions, battleViewModel, width, height, onBattleDetailClick)
}

@Preview
@Composable
fun MiddleBattleScreenPreview() {
    MiddleBattleScreen(
        battleViewModel = BattleViewModel(FakeBattleRepository()),
        gameViewModel = GameViewModel(FakeGameRepository())
    )

}