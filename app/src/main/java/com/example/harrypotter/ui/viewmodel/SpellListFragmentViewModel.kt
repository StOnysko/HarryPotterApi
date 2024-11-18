package com.example.harrypotter.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.data.repository.ServiceRepository
import com.example.harrypotter.ui.models.Spell
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpellListFragmentViewModel @Inject constructor(
    private val serviceRepository: ServiceRepository
) : ViewModel() {

    private val _spellListState = MutableStateFlow<List<Spell>>(emptyList())
    val spellListState: StateFlow<List<Spell>> = _spellListState

    init {
        loadSpells()
    }

    private fun loadSpells() = viewModelScope.launch {
        val retrofitResponse = serviceRepository.getSpells()
        _spellListState.value = retrofitResponse
    }
}