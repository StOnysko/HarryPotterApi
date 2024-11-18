package com.example.harrypotter.ui.fragments.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypotter.databinding.CharacterSpellItemBinding

class CharacterSpellItemViewHolder(binding: CharacterSpellItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): CharacterSpellItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = CharacterSpellItemBinding.inflate(inflater, parent, false)
            return CharacterSpellItemViewHolder(binding)
        }
    }
}