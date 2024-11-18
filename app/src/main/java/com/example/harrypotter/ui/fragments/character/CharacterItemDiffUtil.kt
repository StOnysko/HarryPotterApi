package com.example.harrypotter.ui.fragments.character

import androidx.recyclerview.widget.DiffUtil
import com.example.harrypotter.ui.models.Spell

class CharacterItemDiffUtil : DiffUtil.ItemCallback<Spell>() {
    override fun areItemsTheSame(oldItem: Spell, newItem: Spell): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Spell, newItem: Spell): Boolean {
        return oldItem == newItem
    }
}