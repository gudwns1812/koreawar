package dev.hjp.koreawargame.presentation.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.hjp.koreawargame.presentation.viewmodel.game.GameViewModel
import java.text.DecimalFormat

@Composable
fun GameStatusPanel(
    viewModel: GameViewModel,
    navController: NavController = rememberNavController()
) {
    val goldState = viewModel.gold.collectAsState()
    val economyState = viewModel.economy.collectAsState()
    val researchState = viewModel.research.collectAsState()
    val armyState = viewModel.army.collectAsState()

    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "예산: ${goldState.value.gold}",
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "인구: ${economyState.value.population}",
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "군사력: ${armyState.value.militaryPower}",
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "전투력: ${armyState.value.fightPower.format()}",
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "지지도: ${economyState.value.approvalRate.format()}%",
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "연구력: ${researchState.value.researchPoints}",
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "경제력: ${economyState.value.economyPower}",
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "지역수: ${economyState.value.regionCount}",
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.gameOverEvent.collect {
            if (it) {
                navController.navigate("gameOver")
            }
        }
    }
}

private fun Double.format(): String {
    val df = DecimalFormat("#.##")
    return df.format(this)
}