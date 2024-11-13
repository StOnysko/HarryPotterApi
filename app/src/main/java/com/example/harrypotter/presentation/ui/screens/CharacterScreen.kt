package com.example.harrypotter.presentation.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.harrypotter.R
import com.example.harrypotter.presentation.ui.models.Character
import com.example.harrypotter.presentation.ui.models.Spell
import com.example.harrypotter.presentation.viewmodel.CharacterScreenViewModel
import com.example.harrypotter.util.HouseUtils.houseList
import com.example.harrypotter.util.UrlUtils

@Composable
fun CharacterScreen(
    characterID: String, viewModel: CharacterScreenViewModel = hiltViewModel()
) {

    val character =
        viewModel.loadCharacterByID(characterID).collectAsStateWithLifecycle(Character())
    val spellList = viewModel.spellListState.value
    val dialogState = remember { mutableStateOf(false) }
    val changeHouseDialog = remember { mutableStateOf(false) }

    Box {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.house_backg),
            contentDescription = null
        )

        Column(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ChooseSpellToLearnDialogUI(showDialog = dialogState.value,
                spellList = spellList,
                onSpellSelected = { selectedSpell ->
                    viewModel.saveLearnedSpell(selectedSpell)
                },
                onDismiss = {
                    dialogState.value = false
                })

            ChangeHouseDialogUI(
                showDialog = changeHouseDialog.value,
                onDismiss = { changeHouseDialog.value = false },
                selectedHouse = { selectedHouse ->
                    viewModel.changeHouse(house = selectedHouse, character = character.value)
                    changeHouseDialog.value = false
                },
                housesList = houseList
            )

            Spacer(modifier = Modifier.height(10.dp))

            Image(
                modifier = Modifier
                    .size(200.dp)
                    .clip(shape = RoundedCornerShape(40.dp)),
                contentScale = ContentScale.Crop,
                painter = rememberAsyncImagePainter(model = UrlUtils.getImageByURL(url = character.value.image.orEmpty())),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                text = character.value.name.orEmpty()
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                text = "House: ${UrlUtils.getHouseByUrl(character.value.house.orEmpty())}"
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                fontSize = 25.sp,
                textAlign = TextAlign.Start,
                text = "Additional Info:"
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                text = "Birth: ${UrlUtils.getBirthByUrl(url = character.value.dateOfBirth)}"
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                text = "Species: ${UrlUtils.getSpeciesByUrl(url = character.value.species.orEmpty())}"
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                text = "Gender: ${UrlUtils.getGenderByUrl(url = character.value.gender.orEmpty())}"
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                text = "Ancestry: ${UrlUtils.getAncestryByUrl(url = character.value.ancestry.orEmpty())}"
            )
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                text = "Actor: ${UrlUtils.getActorByByUrl(url = character.value.actor.orEmpty())}"
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                onClick = { changeHouseDialog.value = true }) {
                Text(
                    text = "Change house", fontSize = 25.sp, color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                onClick = { dialogState.value = true }) {
                Text(
                    text = "Teach spell", fontSize = 25.sp, color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                text = "Spells"
            )

            Spacer(modifier = Modifier.height(20.dp))

            LearnedSpells(spellList = character.value.spells)
        }
    }
}

@Composable
private fun ChangeHouseDialogUI(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    selectedHouse: (String) -> Unit,
    housesList: List<String>
) {
    if (showDialog) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    modifier = Modifier
                        .wrapContentSize()
                        .fillMaxWidth(),
                    painter = painterResource(id = R.drawable.house_backg),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )

                Column {
                    housesList.forEach { house ->
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedHouse(house) },
                            text = house,
                            textAlign = TextAlign.Center,
                            fontSize = 30.sp
                        )

                        Spacer(modifier = Modifier.height(30.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun ChooseSpellToLearnDialogUI(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    spellList: List<Spell>,
    onSpellSelected: (Spell) -> Unit
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = onDismiss
        ) {
            Box {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.house_backg),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Select Spell To Learn",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    LazyColumn {
                        items(spellList) { spell ->
                            Row(
                                modifier = Modifier.clickable {
                                    onSpellSelected(spell)
                                    onDismiss()
                                }) {
                                SpellItemView(modifier = Modifier.size(50.dp))
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = spell.name,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LearnedSpells(spellList: List<Spell>) {
    val context = LocalContext.current
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(spellList) { spell ->
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .clickable {
                        Toast
                            .makeText(context, spell.name, Toast.LENGTH_SHORT)
                            .show()
                    },
                painter = painterResource(id = R.drawable.first_spell),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun SpellItemView(modifier: Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.first_spell),
        contentDescription = null
    )
}