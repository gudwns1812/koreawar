package dev.hjp.koreawargame.presentation.ui.tax

import SouthKoreaTaxProvince
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.hjp.koreawargame.R
import dev.hjp.koreawargame.data.repository.battle.FakeBattleRepository
import dev.hjp.koreawargame.data.repository.game.FakeGameRepository
import dev.hjp.koreawargame.presentation.ui.common.GameLayout
import dev.hjp.koreawargame.presentation.ui.common.GameStatusPanel
import dev.hjp.koreawargame.presentation.ui.common.Triangle
import dev.hjp.koreawargame.presentation.viewmodel.game.BattleViewModel
import dev.hjp.koreawargame.presentation.viewmodel.game.GameViewModel

@Composable
fun SouthKoreaTaxScreen(
    viewModel: GameViewModel,
    battleViewModel: BattleViewModel,
    navController: NavController = rememberNavController()
) {
    GameLayout(
        gameViewModel = viewModel,
        battleViewModel = battleViewModel,
        content = {
            BoxWithConstraints(
                modifier = Modifier.fillMaxSize()
            ) {
                val boxWidth = maxWidth
                val boxHeight = maxHeight

                Image(
                    painter = painterResource(R.drawable.south_korea_map_without_bg),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .zIndex(1f),
                    contentScale = ContentScale.Crop,
                )

                Box(modifier = Modifier.fillMaxSize()) {
                    DescriptionSouthKoreaTax()
                }

                Triangle(
                    modifier = Modifier
                        .offset(x = boxWidth.times(0.15f)),
                    sizeDp = 50.dp,
                    description = "북한 지역",
                    angle = 270f
                ) { navController.navigate("northKoreaTax") }

                SouthKoreaTaxContents(
                    boxWidth = boxWidth,
                    boxHeight = boxHeight,
                    viewModel = viewModel
                )

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
                viewModel = viewModel,
                navController = navController
            )
        }
    )
}

@Composable
fun BoxScope.DescriptionSouthKoreaTax() {
    Box(
        modifier = Modifier
            .align(Alignment.TopEnd)
            .zIndex(2f)
    ) {
        val southKorea = SouthKoreaTaxProvince.entries.sortedByDescending { it.taxRate }

        Column {
            Spacer(modifier = Modifier.size(20.dp))
            southKorea.forEach { DescriptionTax(it) }
            DescriptionTaxRule()
        }

    }

}

@Preview
@Composable
fun SouthKoreaTaxScreenPreview() {
    SouthKoreaTaxScreen(
        viewModel = GameViewModel(FakeGameRepository()),
        battleViewModel = BattleViewModel(FakeBattleRepository())
    )
}

@Composable
private fun SouthKoreaTaxContents(
    boxWidth: Dp,
    boxHeight: Dp,
    viewModel: GameViewModel
) {
    TaxBox(
        modifier = Modifier
            .zIndex(1f)
            .offset(boxWidth * 0.3f, boxHeight * 0.89f),
        province = SouthKoreaTaxProvince.JEJU,
        viewModel = viewModel
    )

    TaxBox(
        modifier = Modifier
            .zIndex(1f)
            .offset(boxWidth * 0.25f, boxHeight * 0.66f),
        province = SouthKoreaTaxProvince.JEOLLA,
        viewModel = viewModel
    )

    TaxBox(
        modifier = Modifier
            .zIndex(1f)
            .offset(boxWidth * 0.47f, boxHeight * 0.63f),
        province = SouthKoreaTaxProvince.GYEONGSANG,
        viewModel = viewModel
    )

    TaxBox(
        modifier = Modifier
            .zIndex(1f)
            .offset(boxWidth * 0.3f, boxHeight * 0.52f),
        province = SouthKoreaTaxProvince.CHUNGCHEONG,
        viewModel = viewModel
    )
    TaxBox(
        modifier = Modifier
            .zIndex(1f)
            .offset(boxWidth * 0.48f, boxHeight * 0.43f),
        province = SouthKoreaTaxProvince.GANGWON,
        viewModel = viewModel
    )

    TaxBox(
        modifier = Modifier
            .zIndex(1f)
            .offset(boxWidth * 0.27f, boxHeight * 0.38f),
        province = SouthKoreaTaxProvince.GYEONGGI,
        viewModel = viewModel
    )
}