package com.example.harrypotter.ui.fragments.dialogs.learnSpell

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.harrypotter.ui.fragments.spellList.SpellItemDiffUtil
import com.example.harrypotter.ui.fragments.spellList.SpellItemViewHolder
import com.example.harrypotter.ui.models.Spell

class LearnSpellListAdapter(private val spellClickListener: OnSpellClickListener) : ListAdapter<Spell, SpellItemViewHolder>(SpellItemDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellItemViewHolder {
        return SpellItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SpellItemViewHolder, position: Int) {
        val spell = getItem(position)
        holder.bind(spell)
        holder.itemView.setOnClickListener {
            spellClickListener.onSpellClick(spell)
        }
    }
}