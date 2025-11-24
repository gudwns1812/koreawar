package dev.hjp.koreawargame.presentation.ui.game

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.hjp.koreawargame.data.repository.battle.FakeBattleRepository
import dev.hjp.koreawargame.data.repository.game.FakeGameRepository
import dev.hjp.koreawargame.domain.domaindata.Facilities
import dev.hjp.koreawargame.presentation.ui.common.ItemDescriptionText
import dev.hjp.koreawargame.presentation.ui.common.ItemListScreen
import dev.hjp.koreawargame.presentation.ui.common.ItemNameText
import dev.hjp.koreawargame.presentation.viewmodel.game.BattleViewModel
import dev.hjp.koreawargame.presentation.viewmodel.game.GameViewModel

@Composable
fun FacilitiesScreen(
    viewModel: GameViewModel,
    battleViewModel: BattleViewModel,
    navController: NavController = rememberNavController(),
) {
    ItemListScreen(
        items = Facilities.entries.sortedBy { it.cost },
        viewModel = viewModel,
        battleViewModel = battleViewModel,
        navController = navController,
        onItemClick = { facility -> viewModel.buyFacilities(facility) },
    ) { facility ->
        ItemNameText(facility.displayName)
        ItemDescriptionText("예산 - ${facility.cost}")
        ItemDescriptionText("인구 + ${facility.populationIncrease}")
        ItemDescriptionText("경제력 + ${facility.economyPowerIncrease}")
        ItemDescriptionText("지지도 + ${facility.approvalRateIncrease}%")

        if (facility != Facilities.HOUSE) {
            ItemDescriptionText("지역 ${facility.limitRegionCount}개 이상")
        }

    }
}

@Preview
@Composable
fun FacilitiesScreenPreview() {
    FacilitiesScreen(
        viewModel = GameViewModel(FakeGameRepository()),
        battleViewModel = BattleViewModel(FakeBattleRepository()),
    )
}