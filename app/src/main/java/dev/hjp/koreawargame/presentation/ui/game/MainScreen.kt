package dev.hjp.koreawargame.presentation.ui.game

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.hjp.koreawargame.data.repository.TaxRepository
import dev.hjp.koreawargame.presentation.ui.common.GameLayout
import dev.hjp.koreawargame.presentation.ui.common.GameStatusPanel
import dev.hjp.koreawargame.presentation.ui.common.safeClickable
import dev.hjp.koreawargame.presentation.viewmodel.game.GameViewModel


@Composable
fun MainScreen(
    viewModel: GameViewModel,
    onFactoryClick: () -> Unit = {},
    onFacilityClick: () -> Unit = {},
    onFightClick: () -> Unit = {},
    onResearchClick: () -> Unit = {},
    onTaxClick: () -> Unit = {}
) {
    GameLayout(
        content = {
            BoxWithConstraints(Modifier.fillMaxSize()) {
                val boxWidth = maxWidth
                val boxHeight = maxHeight

                Text(
                    text = "-메인 메뉴-",
                    modifier = Modifier
                        .align(Alignment.TopCenter),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )

                Text(
                    text = "전쟁 준비",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(boxWidth * 0.07f, boxHeight * 0.18f)
                        .safeClickable { onFactoryClick() },
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )

                Text(
                    text = "시설 개발",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(boxWidth * -0.07f, boxHeight * 0.18f)
                        .safeClickable { onFacilityClick() },
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )

                Text(
                    text = "전투 개시",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .safeClickable { onFightClick() },
                    fontSize = 40.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Red
                )

                Text(
                    text = "연구 개발",
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .offset(boxWidth * 0.07f, boxHeight * -0.18f)
                        .safeClickable { onResearchClick() },
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )

                Text(
                    text = "세금 징수",
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(boxWidth * -0.07f, boxHeight * -0.18f)
                        .safeClickable { onTaxClick() },
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )
            }
        },
        bottomContent = {
            GameStatusPanel(viewModel = viewModel) // Uncomment and implement this if you have a GameStatusPanel
        }
    )
}


@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(GameViewModel(TaxRepository()))
}