package com.example.harrypotter.data.local

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.harrypotter.presentation.ui.models.Character

data class CharacterWithSpells(
    @Embedded val characterRoom: CharacterRoom,
    @Relation(
        parentColumn = "characterId",
        entityColumn = "spellId",
        associateBy = Junction(CharacterSpellCross::class)
    )
    val spells: List<SpellRoom>
) {
    fun toCharacter() = Character(
        characterId = characterRoom.characterId,
        actor = characterRoom.actor,
        alive = characterRoom.alive,
        ancestry = characterRoom.ancestry,
        dateOfBirth = characterRoom.dateOfBirth,
        eyeColour = characterRoom.eyeColour,
        gender = characterRoom.gender,
        hairColour = characterRoom.hairColour,
        hogwartsStaff = characterRoom.hogwartsStaff,
        hogwartsStudent = characterRoom.hogwartsStudent,
        house = characterRoom.house,
        image = characterRoom.image,
        patronus = characterRoom.patronus,
        species = characterRoom.species,
        wizard = characterRoom.wizard,
        yearOfBirth = characterRoom.yearOfBirth,
        wandCore = characterRoom.wandCore,
        wandWood = characterRoom.wandWood,
        wandLength = characterRoom.wandLength,
        name = characterRoom.name,
        spells = spells.map { it.toSpell() }
    )

}