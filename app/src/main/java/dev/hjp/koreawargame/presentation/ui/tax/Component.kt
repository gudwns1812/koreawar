package dev.hjp.koreawargame.presentation.ui.tax

import SouthKoreaTaxProvince
import TaxProvince
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.hjp.koreawargame.data.repository.game.FakeGameRepository
import dev.hjp.koreawargame.presentation.ui.theme.ForestGreen
import dev.hjp.koreawargame.presentation.ui.theme.OverlayMediumGray
import dev.hjp.koreawargame.presentation.viewmodel.game.GameViewModel


@Composable
fun TaxBox(
    modifier: Modifier = Modifier,
    province: TaxProvince,
    cooldownMillis: Long = 2000L,
    viewModel: GameViewModel
) {
    var isOnCooldown by remember { mutableStateOf(false) }
    var cooldownProgress by remember { mutableFloatStateOf(1f) }

    val hasRegion = viewModel.isUnLock(province)

    val isEnabled = hasRegion && !isOnCooldown

    Box(
        modifier = modifier
            .border(width = 1.dp, color = Color.Black)
            .size(60.dp, 30.dp)
            .clickable(enabled = isEnabled) {
                viewModel.onProvinceClick(province)
                isOnCooldown = true
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (hasRegion) ForestGreen else OverlayMediumGray)
        )

        if (isOnCooldown) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(cooldownProgress)
                    .background(Color.DarkGray)
            )
        }


        Text(
            text = province.provinceName,
            modifier = Modifier.align(Alignment.Center),
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
        )
    }

    if (isOnCooldown) {
        LaunchedEffect(Unit) {
            val startTime = System.currentTimeMillis()
            val endTime = startTime + cooldownMillis

            while (System.currentTimeMillis() < endTime) {
                cooldownProgress =
                    1f - ((System.currentTimeMillis() - startTime).toFloat() / cooldownMillis)
                kotlinx.coroutines.delay(16)
            }

            cooldownProgress = 1f
            isOnCooldown = false
        }
    }
}

@Composable
fun DescriptionTax(province: TaxProvince) {
    Text(
        text = "${province.provinceName}도에서는 인구의 ${province.taxRate}%",
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp
    )
}

@Composable
fun DescriptionTaxRule() {
    Text(
        text = """
            제주를 제외한 곳에서 세금을
            징수하려면 그 지역에 해당
            하는 땅을 하나라도
            가지고 있어야 합니다.
        """.trimIndent(),
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )
}

@Preview
@Composable
fun TaxBoxPreview() {
    TaxBox(province = SouthKoreaTaxProvince.JEOLLA, viewModel = GameViewModel(FakeGameRepository()))
}