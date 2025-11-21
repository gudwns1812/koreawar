package dev.hjp.koreawargame.presentation.ui.battle

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.hjp.koreawargame.R
import dev.hjp.koreawargame.data.repository.battle.FakeBattleRepository
import dev.hjp.koreawargame.data.repository.game.FakeGameRepository
import dev.hjp.koreawargame.domain.domaindata.war.ShowCities
import dev.hjp.koreawargame.domain.domaindata.war.southCityPositions
import dev.hjp.koreawargame.presentation.ui.common.GameLayout
import dev.hjp.koreawargame.presentation.ui.common.GameStatusPanel
import dev.hjp.koreawargame.presentation.ui.common.Triangle
import dev.hjp.koreawargame.presentation.viewmodel.game.BattleViewModel
import dev.hjp.koreawargame.presentation.viewmodel.game.GameViewModel

@Composable
fun SouthBattleScreen(
    battleViewModel: BattleViewModel,
    gameViewModel: GameViewModel,
    navController: NavController = rememberNavController()
) {
    GameLayout(
        content = {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 15.dp)
            ) {
                val boxWidth = maxWidth
                val boxHeight = maxHeight

                Image(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .size(width = boxWidth, height = boxHeight * 1.1f),
                    painter = painterResource(R.drawable.south_battle_map),
                    contentDescription = "battle_map",
                    contentScale = ContentScale.FillBounds
                )

                SouthBattleBoxContent(
                    boxWidth,
                    boxHeight,
                    battleViewModel
                ) { navController.navigate("battleDetail") }

                Triangle(
                    modifier = Modifier
                        .offset(x = boxWidth.times(0.15f)),
                    sizeDp = 50.dp,
                    description = "중부 지역",
                    angle = 270f
                ) { navController.navigate("middleBattle") }

                Triangle(
                    modifier = Modifier
                        .align(Alignment.BottomStart),
                    sizeDp = 50.dp,
                    description = "메인 화면",
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
fun SouthBattleBoxContent(
    width: Dp,
    height: Dp,
    battleViewModel: BattleViewModel,
    onBattleDetailClick: () -> Unit
) {
    val cityPositions = remember { southCityPositions }

    ShowCities(cityPositions, battleViewModel, width, height, onBattleDetailClick)
}


@Preview
@Composable
fun SouthBattleScreenPreview() {
    SouthBattleScreen(
        BattleViewModel(
            battleRepository = FakeBattleRepository(),
        ),
        GameViewModel(FakeGameRepository())
    )
}