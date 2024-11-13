package com.example.harrypotter.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.harrypotter.R
import com.example.harrypotter.presentation.ui.models.Character
import com.example.harrypotter.presentation.ui.screens.navigation.Screen
import com.example.harrypotter.presentation.ui.theme.HouseItemRowBackground
import com.example.harrypotter.presentation.viewmodel.CharacterListScreenViewModel
import com.example.harrypotter.util.HouseRowUtils.houseRowList
import com.example.harrypotter.util.UrlUtils.getHouseByUrl
import com.example.harrypotter.util.UrlUtils.getImageByURL


@Composable
fun CharacterListScreen(
    navController: NavController,
    viewModel: CharacterListScreenViewModel = hiltViewModel()
) {
    val state = viewModel.allCharacter.collectAsStateWithLifecycle(emptyList())
    val isLoading = viewModel.isLoading.value

    Box {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.house_backg),
            contentDescription = null
        )

        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            else -> {
                Column {
                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    LazyRow(
                        contentPadding = PaddingValues(end = 10.dp, start = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        items(houseRowList) { houseItem ->
                            HouseItemRowView(
                                onClick = {
                                    viewModel.loadCharactersByHouse(house = houseItem)
                                },
                                houseName = houseItem
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "Characters",
                        fontSize = 35.sp,
                        textAlign = TextAlign.Center

                    )

                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(state.value) { character ->
                            CharacterItemView(
                                navController = navController,
                                character = character
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CharacterItemView(
    navController: NavController,
    character: Character
) {
    Row(
        modifier = Modifier
            .clickable {
                navController.navigate("${Screen.CharacterScreen.route}/${character.characterId}")
            }
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    ) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(shape = RoundedCornerShape(20.dp)),
            painter = rememberAsyncImagePainter(model = getImageByURL(url = character.image.orEmpty())),
            contentDescription = null
        )
        Column {
            Text(
                text = character.name.orEmpty(),
                modifier = Modifier.padding(start = 10.dp),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = getHouseByUrl(character.house.orEmpty()),
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}

@Composable
private fun HouseItemRowView(houseName: String, onClick: (String) -> Unit) {
    Text(
        modifier = Modifier
            .clickable { onClick(houseName) }
            .background(color = HouseItemRowBackground, shape = RoundedCornerShape(8.dp))
            .padding(start = 8.dp, end = 8.dp, top = 5.dp, bottom = 5.dp)
            .clip(RoundedCornerShape(8.dp)),
        text = houseName,
        fontSize = 20.sp,
        color = Color.Black
    )
}