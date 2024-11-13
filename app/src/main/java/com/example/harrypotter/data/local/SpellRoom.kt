package com.example.harrypotter.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.harrypotter.presentation.ui.models.Spell

@Entity
data class SpellRoom(
    @PrimaryKey
    val spellId: String = "",
    val description: String = "",
    val name: String = ""
) {
    fun toSpell() = Spell(
        id = spellId,
        description = description,
        name = name
    )
}