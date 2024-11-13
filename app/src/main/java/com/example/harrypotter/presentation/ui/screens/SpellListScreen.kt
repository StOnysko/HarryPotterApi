package com.example.harrypotter.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.harrypotter.R
import com.example.harrypotter.presentation.ui.models.Spell
import com.example.harrypotter.presentation.viewmodel.SpellListScreenViewModel
import com.example.harrypotter.util.UrlUtils


@Composable
fun SpellListScreen(viewModel: SpellListScreenViewModel = hiltViewModel()) {
    val spellListState = viewModel.spellListState.value
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
                Column(modifier = Modifier.fillMaxSize()) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 35.sp,
                        text = "Spells guide"
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(spellListState) { spell ->
                            SpellItemView(spell = spell)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SpellItemView(spell: Spell) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    ) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(shape = RoundedCornerShape(20.dp)),
            painter = painterResource(id = R.drawable.third_spell),
            contentDescription = null
        )
        Column {
            Text(
                text = UrlUtils.getSpellNameByUrl(spell.name),
                modifier = Modifier.padding(start = 10.dp),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = UrlUtils.getSpellDescriptionByUrl(spell.description),
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}