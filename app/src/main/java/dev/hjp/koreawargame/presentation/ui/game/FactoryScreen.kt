package dev.hjp.koreawargame.presentation.ui.game

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.hjp.koreawargame.data.repository.TaxRepository
import dev.hjp.koreawargame.domain.domaindata.UnitType
import dev.hjp.koreawargame.presentation.ui.common.ItemDescriptionText
import dev.hjp.koreawargame.presentation.ui.common.ItemListScreen
import dev.hjp.koreawargame.presentation.ui.common.ItemNameText
import dev.hjp.koreawargame.presentation.viewmodel.game.GameViewModel

@Composable
fun FactoryScreen(
    viewModel: GameViewModel,
    onBackClick: () -> Unit = { }
) {
    ItemListScreen(
        items = UnitType.entries.sortedBy { it.cost },
        viewModel = viewModel,
        onItemClick = { unit -> viewModel.buyUnit(unit) },
        on10ItemClick = { unit -> viewModel.buyUnit10Times(unit) },
        onBackClick = onBackClick
    ) { unitType ->
        ItemNameText(unitType.displayName)
        ItemDescriptionText("예산 - ${unitType.cost}")
        ItemDescriptionText("군사력 + ${unitType.attackPower}")

        if (unitType != UnitType.INFANTRY) {
            ItemDescriptionText("연구력 ${unitType.researchLimit} 이상")
        }

        if (unitType == UnitType.NUCLEAR_MISSILE) {
            ItemDescriptionText("지지도 -2%")
        }
    }
}

@Preview
@Composable
fun FactoryScreenPreview() {
    FactoryScreen(viewModel = GameViewModel(TaxRepository()))
}