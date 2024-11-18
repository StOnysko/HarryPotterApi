package com.example.harrypotter.ui.fragments.characterList

import com.example.harrypotter.ui.models.Character

interface OnItemClickListener {
    fun onCharacterClickListener(character: Character)
}