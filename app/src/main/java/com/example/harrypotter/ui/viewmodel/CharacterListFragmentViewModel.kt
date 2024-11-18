package com.example.harrypotter.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.data.repository.ServiceRepository
import com.example.harrypotter.util.HouseUtils.ALL_HOUSES
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.harrypotter.ui.models.Character

@HiltViewModel
class CharacterListFragmentViewModel @Inject constructor(
    private val repository: ServiceRepository
) : ViewModel() {

    var allCharacter: MutableStateFlow<List<Character>> = MutableStateFlow(emptyList())
    private var job: Job? = null

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        job?.cancel()
        job = viewModelScope.launch {
            repository.listenToAllCharacters().collect { characterList ->
                allCharacter.value = characterList
            }
        }
    }

    fun loadCharactersByHouse(house: String) {
        job?.cancel()
        if (house.equals(ALL_HOUSES)) {
            loadCharacters()
        } else {
            job = viewModelScope.launch {
                repository.listenToAllCharactersByHouse(house).collect { characterList ->
                    allCharacter.value = characterList
                }
            }
        }
    }
}