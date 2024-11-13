package com.example.harrypotter.data.local

import androidx.room.Entity

@Entity(primaryKeys = ["characterId", "spellId"])
data class CharacterSpellCross(
    val characterId: String,
    val spellId: String
)