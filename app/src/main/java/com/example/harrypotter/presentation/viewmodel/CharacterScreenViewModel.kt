package com.example.harrypotter.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.data.repository.ServiceRepository
import com.example.harrypotter.presentation.ui.models.Character
import com.example.harrypotter.presentation.ui.models.Spell
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterScreenViewModel @Inject constructor(
    private val serviceRepository: ServiceRepository
) : ViewModel() {

    private var characterId: String = ""
    private val _spellListState = mutableStateOf<List<Spell>>(emptyList())
    val spellListState: State<List<Spell>> = _spellListState

    init {
        loadSpellList()
    }

    fun loadCharacterByID(id: String): Flow<Character> {
        characterId = id
        return serviceRepository.listenToCharacter(id)
    }

    fun changeHouse(character: Character, house: String) = viewModelScope.launch {
        serviceRepository.changeCharacterHouse(character = character, house = house)
    }

    private fun loadSpellList() = viewModelScope.launch {
        val retrofitResponse = serviceRepository.getSpells()
        _spellListState.value = retrofitResponse
    }

    fun saveLearnedSpell(spell: Spell) {
        viewModelScope.launch(Dispatchers.IO) {
            serviceRepository.teachSpell(characterId, spell.id)
        }
    }
}