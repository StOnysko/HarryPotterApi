package com.example.harrypotter.ui.fragments.characterList

import androidx.recyclerview.widget.DiffUtil
import com.example.harrypotter.ui.models.Character

class CharacterItemDiffUtil : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.characterId == newItem.characterId
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}