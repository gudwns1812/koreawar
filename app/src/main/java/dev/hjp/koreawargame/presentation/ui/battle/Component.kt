package dev.hjp.koreawargame.presentation.ui.battle

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.hjp.koreawargame.domain.domaindata.war.BattleCity
import dev.hjp.koreawargame.presentation.ui.common.safeClickable
import dev.hjp.koreawargame.ui.theme.ForestGreen
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun BattleBox(
    modifier: Modifier = Modifier,
    city: BattleCity,
    fixedTextColor: Color? = null,
    onClick: () -> Unit = {}
) {
    var text = city.regionName
    var textColor = Color.Black

    if (city.isCapital) {
        text = if (city.militaryPower > 500_000L) text.plus("\nHARD") else text
        textColor = Color.Red
    }

    Box(
        modifier = modifier
            .border(width = 2.dp, color = Color.Black)
            .size(
                width = 50.dp,
                height = if (text.length < 5) 30.dp else 60.dp
            )
            .safeClickable { onClick() }
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.Center),
            color = fixedTextColor ?: textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
        )
    }
}

@Composable
fun ClearBox(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(2.dp, Color.Black)
            .size(
                width = 50.dp,
                height = 30.dp
            )
            .background(color = ForestGreen)
    ) {
        Text(
            text = "점령",
            modifier = Modifier.align(Alignment.Center),
            color = Color.Black,
            fontSize = 16.sp,
        )
    }
}

@Composable
fun BattleStar(
    text: String,
    modifier: Modifier = Modifier,
    starColor: Color = Color.Red,
    strokeColor: Color = Color.Black,
    textColor: Color = Color.White,
    textSize: TextUnit = 16.sp,
    strokeWidth: Float = 6f
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Canvas(modifier = Modifier.matchParentSize()) {

            val radius = size.minDimension / 2f
            val innerRadius = radius * 0.5f
            val center = Offset(size.width / 2f, size.height / 2f)

            val path = Path()

            val outerAngles = listOf(-90, -18, 54, 126, 198)
            val innerAngles = listOf(-54, 18, 90, 162, 234)

            fun outerPoint(angle: Int): Offset {
                val rad = Math.toRadians(angle.toDouble())
                return Offset(
                    center.x + radius * cos(rad).toFloat(),
                    center.y + radius * sin(rad).toFloat()
                )
            }

            fun innerPoint(angle: Int): Offset {
                val rad = Math.toRadians(angle.toDouble())
                return Offset(
                    center.x + innerRadius * cos(rad).toFloat(),
                    center.y + innerRadius * sin(rad).toFloat()
                )
            }

            path.moveTo(outerPoint(outerAngles[0]).x, outerPoint(outerAngles[0]).y)

            for (i in 1 until 5) {
                val iInner = innerPoint(innerAngles[i - 1])
                val iOuter = outerPoint(outerAngles[i])
                path.lineTo(iInner.x, iInner.y)
                path.lineTo(iOuter.x, iOuter.y)
            }

            path.lineTo(innerPoint(innerAngles.last()).x, innerPoint(innerAngles.last()).y)
            path.close()

            drawPath(
                path = path,
                color = starColor,
                style = Fill
            )

            drawPath(
                path = path,
                color = strokeColor,
                style = Stroke(width = strokeWidth)
            )
        }

        Text(
            text = text,
            color = textColor,
            fontSize = textSize
        )
    }
}


@Preview
@Composable
fun BattleBoxPreview() {
    BattleBox(city = BattleCity(1, "해남", 200L))
}

@Preview
@Composable
fun BattleStarPreview() {
    BattleStar("서울")
}

@Preview
@Composable
fun ClearBoxPreview() {
    ClearBox()
}