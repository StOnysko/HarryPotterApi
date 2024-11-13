package com.example.harrypotter.data.model

import com.example.harrypotter.data.local.SpellRoom


data class SpellAPIModel(
    val id: String = "",
    val description: String?,
    val name: String?
) {
    fun toSpellRoom() = SpellRoom(
        spellId = id,
        description = description ?: "",
        name = name ?: ""
    )
}