package dev.hjp.koreawargame.presentation.ui.tax

import NorthKoreaTaxProvince
import NorthKoreaTaxProvince.HAMGYEONG
import NorthKoreaTaxProvince.HWANGHAE
import NorthKoreaTaxProvince.PYEONGAN
import NorthKoreaTaxProvince.WONSAN
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
import dev.hjp.koreawargame.data.repository.game.FakeGameRepository
import dev.hjp.koreawargame.presentation.ui.common.GameLayout
import dev.hjp.koreawargame.presentation.ui.common.GameStatusPanel
import dev.hjp.koreawargame.presentation.ui.common.Triangle
import dev.hjp.koreawargame.presentation.viewmodel.game.GameViewModel

@Composable
fun NorthKoreaTaxScreen(
    viewModel: GameViewModel,
    navController: NavController = rememberNavController()
) {
    GameLayout(
        content = {
            BoxWithConstraints(
                modifier = Modifier.fillMaxSize()
            ) {
                val boxWidth = maxWidth
                val boxHeight = maxHeight

                Image(
                    painter = painterResource(R.drawable.north_korea_map),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .size(boxWidth * 2f, boxHeight * 0.9f)
                        .zIndex(1f),
                    contentScale = ContentScale.FillBounds,
                )

                NorthKoreaTaxContents(boxWidth, boxHeight, viewModel)

                Box(modifier = Modifier.fillMaxSize()) {
                    DescriptionNorthKoreaTax()
                }

                Triangle(
                    modifier = Modifier
                        .align(Alignment.BottomCenter),
                    sizeDp = 50.dp,
                    description = "남한 지역",
                    angle = 90f
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
fun NorthKoreaTaxContents(
    boxWidth: Dp,
    bodHeight: Dp,
    viewModel: GameViewModel
) {
    TaxBox(
        modifier = Modifier
            .zIndex(1f)
            .offset(boxWidth * 0.24f, bodHeight * 0.8f),
        province = HWANGHAE,
        viewModel = viewModel
    )

    TaxBox(
        modifier = Modifier
            .zIndex(1f)
            .offset(boxWidth * 0.44f, bodHeight * 0.6f),
        province = WONSAN,
        viewModel = viewModel
    )
    TaxBox(
        modifier = Modifier
            .zIndex(1f)
            .offset(boxWidth * 0.22f, bodHeight * 0.55f),
        province = PYEONGAN,
        viewModel = viewModel
    )
    TaxBox(
        modifier = Modifier
            .zIndex(1f)
            .offset(boxWidth * 0.55f, bodHeight * 0.44f),
        province = HAMGYEONG,
        viewModel = viewModel
    )

}

@Composable
fun BoxScope.DescriptionNorthKoreaTax() {
    Box(
        modifier = Modifier
            .align(Alignment.TopStart)
            .zIndex(2f)
    ) {
        val southKorea = NorthKoreaTaxProvince.entries.sortedByDescending { it.taxRate }

        Column {
            Spacer(modifier = Modifier.size(20.dp))
            southKorea.forEach { DescriptionTax(it) }
            DescriptionTaxRule()
        }

    }

}

@Preview
@Composable
fun NorthKoreaTaxPreview() {
    NorthKoreaTaxScreen(viewModel = GameViewModel(FakeGameRepository()))
}

