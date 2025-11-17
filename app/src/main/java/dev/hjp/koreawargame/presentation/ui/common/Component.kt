package dev.hjp.koreawargame.presentation.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath

@Composable
fun Triangle(
    modifier: Modifier = Modifier,
    sizeDp: Dp = 60.dp,
    description: String = "",
    angle: Float = 180f,
    onClick: () -> Unit = { }
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .safeClickable { onClick() }
                .drawWithCache {
                    val roundedPolygon = RoundedPolygon(
                        numVertices = 3,
                        radius = size.minDimension / 2,
                        centerX = size.width / 2,
                        centerY = size.height / 2,
                        rounding = CornerRounding(
                            size.minDimension / 10f,
                            smoothing = 0.1f
                        )
                    )
                    val roundedPolygonPath = roundedPolygon.toPath().asComposePath()

                    onDrawBehind {
                        rotate(degrees = angle, pivot = center) {
                            drawPath(roundedPolygonPath, color = Color.DarkGray)
                        }
                    }
                }
                .size(size = sizeDp)
        )
        Text(
            text = description,
            modifier = modifier,
            fontWeight = FontWeight.Bold,
        )
    }
}

fun Modifier.safeClickable(
    cooldownMillis: Long = 1000L,
    enabled: Boolean = true,
    onClick: () -> Unit
) = composed {

    var lastClicked by remember { mutableStateOf(0L) }

    clickable(enabled = enabled) {
        val now = System.currentTimeMillis()
        if (now - lastClicked > cooldownMillis) {
            lastClicked = now
            onClick()
        }
    }
}

@Composable
fun ItemNameText(
    name: String,
) {
    Text(
        text = name,
        fontSize = 20.sp,
        fontWeight = FontWeight.ExtraBold
    )
}

@Composable
fun ItemDescriptionText(
    description: String,
) {
    Text(
        text = description,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )
}
