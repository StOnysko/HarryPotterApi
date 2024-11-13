package com.example.harrypotter.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.data.repository.ServiceRepository
import com.example.harrypotter.presentation.ui.models.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListScreenViewModel @Inject constructor(
    private val serviceRepository: ServiceRepository
) : ViewModel() {

    private val _isLoading = mutableStateOf(false)

    val isLoading: State<Boolean> = _isLoading

    var allCharacter: MutableStateFlow<List<Character>> = MutableStateFlow(emptyList())

    private var job: Job? = null

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        job?.cancel()
        job = viewModelScope.launch {
            serviceRepository.listenToAllCharacters().collect { characterList ->
                allCharacter.value = characterList
            }
        }
    }

    fun loadCharactersByHouse(house: String) {
        job?.cancel()
        if (house == "All houses") {
            loadCharacters()
        } else {
            job = viewModelScope.launch {
                serviceRepository.listenToAllCharactersByHouse(house).collect { characterList ->
                    allCharacter.value = characterList
                }
            }
        }
    }
}