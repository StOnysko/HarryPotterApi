package com.example.harrypotter.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.harrypotter.presentation.ui.models.Character

@Entity
data class CharacterRoom(
    @PrimaryKey
    val characterId: String,
    val actor: String? = "",
    val alive: Boolean? = null,
    val ancestry: String? = "",
    val dateOfBirth: String? = "",
    val eyeColour: String? = "",
    val gender: String? = "",
    val hairColour: String? = "",
    val hogwartsStaff: Boolean? = null,
    val hogwartsStudent: Boolean? = null,
    var house: String? = "",
    val image: String? = "",
    val patronus: String? = "",
    val species: String? = "",
    val wizard: Boolean? = null,
    val yearOfBirth: Int? = 0,
    val wandCore: String? = "",
    val wandLength: Double? = 0.0,
    val wandWood: String? = "",
    val name: String? = ""
) {
    fun toCharacter() = Character(
        characterId = characterId,
        actor = actor ?: "",
        alive = alive ?: false,
        ancestry = ancestry ?: "",
        dateOfBirth = dateOfBirth ?: "",
        eyeColour = eyeColour ?: "",
        gender = gender ?: "",
        hairColour = hairColour ?: "",
        hogwartsStaff = hogwartsStaff ?: false,
        hogwartsStudent = hogwartsStudent ?: false,
        house = house ?: "",
        image = image ?: "",
        patronus = patronus ?: "",
        species = species ?: "",
        wizard = wizard ?: false,
        yearOfBirth = yearOfBirth ?: 0,
        wandCore = wandCore ?: "",
        wandLength = wandLength ?: 0.0,
        wandWood = wandWood ?: "",
        name = name ?: "",
    )
}