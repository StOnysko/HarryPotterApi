package com.example.harrypotter.ui.fragments.spellList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypotter.R
import com.example.harrypotter.databinding.SpellItemBinding
import com.example.harrypotter.ui.models.Spell

class SpellItemViewHolder(private val binding: SpellItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(spell: Spell) = with(binding) {
        spellName.text = spell.name
        spellDescription.text = spell.description
        spellImage.setImageResource(R.drawable.third_spell)
    }

    companion object {
        fun from(parent: ViewGroup): SpellItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = SpellItemBinding.inflate(inflater, parent, false)
            return SpellItemViewHolder(binding)
        }
    }
}