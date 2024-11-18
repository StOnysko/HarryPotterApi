package com.example.harrypotter.ui.fragments.characterList

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.harrypotter.ui.models.Character

class CharacterListAdapter(private val onClick: OnItemClickListener) :
    ListAdapter<Character, CharacterItemViewHolder>(CharacterItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        return CharacterItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)

        holder.itemView.setOnClickListener {
            onClick.onCharacterClickListener(character)
        }
    }
}