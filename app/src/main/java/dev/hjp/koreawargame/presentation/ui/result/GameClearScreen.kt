package dev.hjp.koreawargame.presentation.ui.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.hjp.koreawargame.R
import dev.hjp.koreawargame.presentation.ui.common.Triangle

@Composable
fun GameClearScreen(
    navController: NavController = rememberNavController(),
    onReset: () -> Unit = {}
) {

    onReset()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.backgroud_map),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = "-게임 클리어-",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(15.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .border(2.dp, Color.Black)
                .size(300.dp)
                .background(color = Color.LightGray)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(20.dp)
            ) {
                Text(
                    text = "한반도 통일 성공!",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(Modifier.size(20.dp))
                Text(
                    text = """
                        축하합니다!!
                        한반도를 통일하는데 성공하셨습니다!
                        게임을 플레이 해주셔서 감사합니다
                    """.trimIndent(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Triangle(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(5.dp),
            sizeDp = 50.dp,
            description = "메인 화면",
        ) {
            navController.navigate("main") {
                popUpTo("gameOver") {
                    inclusive = true
                }
            }
        }
    }
}

@Preview
@Composable
fun GameClearScreenPreview() {
    GameClearScreen()
}