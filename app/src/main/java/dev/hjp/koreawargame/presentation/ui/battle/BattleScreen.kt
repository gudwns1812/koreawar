package dev.hjp.koreawargame.presentation.ui.battle

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.hjp.koreawargame.R
import dev.hjp.koreawargame.data.repository.battle.FakeBattleRepository
import dev.hjp.koreawargame.data.repository.game.FakeGameRepository
import dev.hjp.koreawargame.presentation.viewmodel.game.BattleViewModel
import dev.hjp.koreawargame.presentation.viewmodel.game.GameViewModel
import kotlinx.coroutines.delay

@Composable
fun BattleScreen(
    navController: NavController = rememberNavController(),
    gameViewModel: GameViewModel,
    battleViewModel: BattleViewModel,
    onStartBattle: () -> Unit = {}
) {
    onStartBattle()

    val currentTarget = battleViewModel.currentTarget.collectAsState().value
    val color = battleViewModel.selectedCountry.value?.color ?: Color.Yellow

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.battle_map),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EnemyMilitaryBox(
                color = color,
                battleCity = currentTarget,
                boxSize = DpSize(250.dp, 150.dp)
            )
            ShakingTank(
                image = R.drawable.tank_up
            )
        }

        Shoot(
            navController = navController,
            battleViewModel = battleViewModel,
            gameViewModel
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShakingTank(
                image = R.drawable.tank_down,
                size = 120.dp
            )
            UserMilitaryBox(
                gameViewModel = gameViewModel,
                boxSize = DpSize(250.dp, 150.dp)
            )
        }
    }
}

@Composable
private fun ShakingTank(
    modifier: Modifier = Modifier,
    size: Dp = 200.dp,
    image: Int
) {
    val offsetY = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            offsetY.animateTo(
                targetValue = -3f,
                animationSpec = tween(
                    durationMillis = 200,
                    easing = LinearEasing
                )
            )

            offsetY.animateTo(
                targetValue = 3f,
                animationSpec = tween(
                    durationMillis = 200,
                    easing = LinearEasing
                )
            )
        }
    }

    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        modifier = modifier
            .size(size = size)
            .offset(y = offsetY.value.dp)
    )
}

@Composable
fun Shoot(
    navController: NavController,
    battleViewModel: BattleViewModel,
    gameViewModel: GameViewModel
) {
    var bulletVisible by remember { mutableStateOf(false) }
    var isTopTurn by remember { mutableStateOf(true) }
    val currentTarget = battleViewModel.currentTarget.collectAsState().value

    LaunchedEffect(Unit) {
        battleViewModel.uiEvent.collect { event ->
            when (event) {
                BattleViewModel.UiEvent.Win -> navController.navigate("result/win")

                BattleViewModel.UiEvent.Lose -> navController.navigate("result/lose")

                else -> {}
            }
        }
    }

    val bulletY = remember { Animatable(280f) }
    LaunchedEffect(currentTarget) {
        while (true) {
            bulletVisible = true
            if (isTopTurn) {
                bulletY.snapTo(280f)
                bulletY.animateTo(580f, tween(1000))
                gameViewModel.damagePlayer(battleViewModel.enemyHp())

            } else {
                bulletY.snapTo(580f)
                bulletY.animateTo(280f, tween(1000))
                battleViewModel.damageEnemy(gameViewModel.army.value.militaryPower)
            }

            bulletVisible = false
            isTopTurn = !isTopTurn
            battleViewModel.proceedTurn(gameViewModel.army.value.militaryPower)

            delay(1000)
        }
    }

    val bulletImage = if (isTopTurn) {
        R.drawable.bomb_down
    } else {
        R.drawable.bomb_up
    }

    Box(Modifier.fillMaxSize()) {
        if (bulletVisible) {
            Image(
                painter = painterResource(bulletImage),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .offset(y = bulletY.value.dp)
                    .align(Alignment.TopCenter)
            )
        }
    }
}


@Preview
@Composable
fun BattleScreenPreview() {
    BattleScreen(
        gameViewModel = GameViewModel(FakeGameRepository()),
        battleViewModel = BattleViewModel(
            FakeBattleRepository(),
        )
    )
}