package com.example.harrypotter.ui.fragments.character

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.harrypotter.ui.fragments.dialogs.learnSpell.OnSpellClickListener
import com.example.harrypotter.ui.models.Spell

class CharacterSpellListAdapter(private val spellClickListener: OnSpellClickListener) :
    ListAdapter<Spell, CharacterSpellItemViewHolder>(CharacterItemDiffUtil()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterSpellItemViewHolder {
        return CharacterSpellItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CharacterSpellItemViewHolder, position: Int) {
        val spell = getItem(position)
        holder.itemView.setOnClickListener {
            spellClickListener.onSpellClick(spell)
        }
    }
}