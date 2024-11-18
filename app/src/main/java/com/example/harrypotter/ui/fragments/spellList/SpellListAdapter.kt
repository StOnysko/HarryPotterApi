package com.example.harrypotter.ui.fragments.spellList

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.harrypotter.ui.models.Spell

class SpellListAdapter :
    ListAdapter<Spell, SpellItemViewHolder>(SpellItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellItemViewHolder {
        return SpellItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SpellItemViewHolder, position: Int) {
        val spell = getItem(position)
        holder.bind(spell)
    }
}