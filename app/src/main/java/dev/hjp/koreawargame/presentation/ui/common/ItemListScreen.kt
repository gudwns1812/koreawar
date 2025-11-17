package dev.hjp.koreawargame.presentation.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.hjp.koreawargame.domain.domaindata.GameItem
import dev.hjp.koreawargame.domain.domaindata.UnitType
import dev.hjp.koreawargame.presentation.viewmodel.game.GameViewModel

@Composable
fun <T : GameItem> ItemListScreen(
    items: List<T>,
    viewModel: GameViewModel,
    onItemClick: (T) -> Unit,
    on10ItemClick: ((T) -> Unit) = { },
    onBackClick: () -> Unit = { },
    descriptionSection: @Composable (T) -> Unit = {}
) {

    GameLayout(
        content = {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    items(items) { item ->
                        GameItemCard(
                            gameItem = item,
                            onClick = { onItemClick(item) },
                            on10Click = { on10ItemClick(item) },
                            descriptionSection = descriptionSection
                        )
                    }
                }

                Triangle(
                    modifier = Modifier
                        .align(Alignment.Start),
                    sizeDp = 50.dp,
                    description = "메인 화면",
                ) { onBackClick() }
            }
        },
        bottomContent = { GameStatusPanel(viewModel = viewModel) }
    )
}

@Composable
fun <T : GameItem> GameItemCard(
    gameItem: T,
    descriptionSection: @Composable (T) -> Unit = {},
    onClick: (T) -> Unit,
    on10Click: ((T) -> Unit) = { }
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = gameItem.imageRes),
                contentDescription = gameItem.displayName,
                modifier = Modifier
                    .size(100.dp)
                    .clickable { onClick(gameItem) }
            )
            Column(modifier = Modifier.padding(start = 8.dp)) {
                descriptionSection(gameItem)

                if (gameItem is UnitType) {
                    Box(
                        modifier = Modifier
                            .background(Color.LightGray)
                            .border(BorderStroke(2.dp, Color.Black))
                            .clickable { on10Click(gameItem) }
                    ) {
                        Text(
                            text = "10회 생산",
                            modifier = Modifier.padding(4.dp),
                            fontSize = 14.sp
                        )
                    }
                }

            }
        }
    }
}