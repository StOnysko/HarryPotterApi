package com.example.harrypotter.presentation.ui.models

import com.example.harrypotter.data.local.CharacterRoom


data class Character(
    val characterId: String = "",
    val actor: String? = "",
    val alive: Boolean? = false,
    val ancestry: String? = "",
    val dateOfBirth: String? = "",
    val eyeColour: String? = "",
    val gender: String? = "",
    val hairColour: String? = "",
    val hogwartsStaff: Boolean? = false,
    val hogwartsStudent: Boolean? = false,
    val house: String? = "",
    val image: String? = "",
    val patronus: String? = "",
    val species: String? = "",
    val wizard: Boolean? = false,
    val yearOfBirth: Int? = 0,
    val wandCore: String? = "",
    val wandLength: Double? = 0.0,
    val wandWood: String? = "",
    val name: String? = "",
    val spells: List<Spell> = listOf()
) {
    fun toCharacterRoom() = CharacterRoom(
        characterId = characterId,
        actor = actor,
        alive = alive,
        ancestry = ancestry,
        dateOfBirth = dateOfBirth,
        eyeColour = eyeColour,
        gender = gender,
        hairColour = hairColour,
        hogwartsStaff = hogwartsStaff,
        hogwartsStudent = hogwartsStudent,
        house = house,
        image = image,
        patronus = patronus,
        species = species,
        wizard = wizard,
        yearOfBirth = yearOfBirth,
        wandWood = wandWood,
        wandCore = wandCore,
        wandLength = wandLength,
        name = name,
    )
}