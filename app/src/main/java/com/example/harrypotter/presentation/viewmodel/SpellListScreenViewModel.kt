package com.example.harrypotter.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.data.repository.ServiceRepository
import com.example.harrypotter.presentation.ui.models.Spell
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpellListScreenViewModel @Inject constructor(
    private val serviceRepository: ServiceRepository
) : ViewModel() {
    private val _spellListState = mutableStateOf<List<Spell>>(emptyList())
    val spellListState: State<List<Spell>> = _spellListState

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
        loadSpells()
    }

    private fun loadSpells() = viewModelScope.launch {
        _isLoading.value = true
        try {
            val retrofitResponse = serviceRepository.getSpells()
            _spellListState.value = retrofitResponse
        } finally {
            _isLoading.value = false
        }
    }
}