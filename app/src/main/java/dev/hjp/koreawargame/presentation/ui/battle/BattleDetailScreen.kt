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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.hjp.koreawargame.R
import dev.hjp.koreawargame.data.repository.battle.FakeBattleRepository
import dev.hjp.koreawargame.data.repository.game.FakeGameRepository
import dev.hjp.koreawargame.domain.domaindata.war.BattleCity
import dev.hjp.koreawargame.presentation.ui.common.Triangle
import dev.hjp.koreawargame.presentation.ui.common.safeClickable
import dev.hjp.koreawargame.presentation.viewmodel.game.BattleViewModel
import dev.hjp.koreawargame.presentation.viewmodel.game.GameViewModel
import dev.hjp.koreawargame.ui.theme.ForestGreen

@Composable
fun BattleDetailScreen(
    battleViewModel: BattleViewModel,
    gameViewModel: GameViewModel,
    navController: NavController = rememberNavController()
) {
    val battleCity = battleViewModel.currentTarget.collectAsState().value
    val color = battleViewModel.selectedCountry.value?.color ?: Color.Yellow

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.backgroud_map),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(100.dp))

            EnemyMilitaryBox(
                color = color,
                battleCity = battleCity
            )

            Text(
                text = "전투 개시",
                fontSize = 30.sp,
                modifier = Modifier
                    .safeClickable {
                        gameViewModel.battle()
                        navController.navigate("battle")
                    },
                color = Color.Red,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )

            UserMilitaryBox(gameViewModel)

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
        ) { navController.popBackStack() }
    }
}

@Composable
fun EnemyMilitaryBox(
    color: Color?,
    boxSize: DpSize = DpSize(300.dp, 200.dp),
    battleCity: BattleCity
) {
    Box(
        modifier = Modifier
            .border(1.dp, Color.Black)
            .size(boxSize)
            .background(color = color ?: Color.Yellow)
    ) {
        val militaryPower = battleCity.militaryPower

        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = battleCity.regionName,
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
}

@Composable
fun UserMilitaryBox(
    gameViewModel: GameViewModel,
    boxSize: DpSize = DpSize(300.dp, 200.dp)
) {
    Box(
        modifier = Modifier
            .border(1.dp, Color.Black)
            .size(boxSize)
            .background(color = ForestGreen)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val userMilitary = gameViewModel.army.collectAsState().value

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
}

@Preview
@Composable
fun BattleDetailScreenPreview() {
    BattleDetailScreen(
        battleViewModel = BattleViewModel(
            battleRepository = FakeBattleRepository()
        ),
        gameViewModel = GameViewModel(
            FakeGameRepository()
        )
    )
}
