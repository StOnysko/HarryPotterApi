package com.example.harrypotter.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.processor.Context
import com.example.harrypotter.data.repository.ServiceRepository
import com.example.harrypotter.ui.models.Character
import com.example.harrypotter.ui.models.Spell
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharacterScreenViewModel @Inject constructor(
    private val serviceRepository: ServiceRepository
) : ViewModel() {

    private var characterId: String = ""
    private val _spellListState = MutableStateFlow<List<Spell>>(emptyList())
    val spellListState: StateFlow<List<Spell>> = _spellListState
    private val _character = MutableStateFlow<Character>(Character())
    val character: StateFlow<Character> = _character

    init {
        loadSpellList()
    }

   fun loadCharacterByID(id: String) {
        characterId = id
        viewModelScope.launch(Dispatchers.IO) {
            serviceRepository.listenToCharacter(id).collect { character ->
                withContext(Dispatchers.Main) {
                    _character.value = character
                }
            }
        }
    }

    fun changeHouse(house: String) = viewModelScope.launch {
        val char = character.value
        serviceRepository.changeCharacterHouse(
            character = char,
            house = house
        )
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