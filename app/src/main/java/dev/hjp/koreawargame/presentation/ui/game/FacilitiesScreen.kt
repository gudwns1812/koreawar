package dev.hjp.koreawargame.presentation.ui.game

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.hjp.koreawargame.data.repository.TaxRepository
import dev.hjp.koreawargame.domain.domaindata.Facilities
import dev.hjp.koreawargame.presentation.ui.common.ItemDescriptionText
import dev.hjp.koreawargame.presentation.ui.common.ItemListScreen
import dev.hjp.koreawargame.presentation.ui.common.ItemNameText
import dev.hjp.koreawargame.presentation.viewmodel.game.GameViewModel

@Composable
fun FacilitiesScreen(
    viewModel: GameViewModel,
    onBackClick: () -> Unit = { }
) {
    ItemListScreen(
        items = Facilities.entries.sortedBy { it.cost },
        viewModel = viewModel,
        onItemClick = { facility -> viewModel.buyFacilities(facility) },
        onBackClick = onBackClick
    ) { facility ->
        ItemNameText(facility.displayName)
        ItemDescriptionText("예산 - ${facility.cost}")
        ItemDescriptionText("인구 + ${facility.populationIncrease}%")
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
    FacilitiesScreen(viewModel = GameViewModel(TaxRepository()))
}