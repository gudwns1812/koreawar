package dev.hjp.koreawargame.presentation.ui.game

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.hjp.koreawargame.domain.domaindata.ResearchItem
import dev.hjp.koreawargame.presentation.ui.common.ItemListScreen
import dev.hjp.koreawargame.presentation.viewmodel.game.GameViewModel

@Composable
fun ResearchScreen(
    viewModel: GameViewModel,
    onBackClick: () -> Unit = { }
) {
    ItemListScreen(
        items = ResearchItem.entries.sortedBy { it.cost },
        viewModel = viewModel,
        onItemClick = { research -> viewModel.buyResearch(research) },
        onBackClick = onBackClick
    ) { research ->
        Text(
            text = research.displayName,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "예산 - ${research.cost}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "연구력 + ${research.researchPointIncrease}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        if (research != ResearchItem.BASIC_RESEARCH) {
            Text(
                text = "경제력 ${research.limitEconomyPower} 이상",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun ResearchScreenPreview() {
    ResearchScreen(viewModel = GameViewModel())
}