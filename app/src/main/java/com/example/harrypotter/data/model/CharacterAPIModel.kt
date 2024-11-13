package com.example.harrypotter.data.model

import com.example.harrypotter.data.local.CharacterRoom

data class CharacterAPIModel(
    val id: String,
    val actor: String?,
    val alive: Boolean?,
    val alternate_actors: List<String>?,
    val alternate_names: List<String>?,
    val ancestry: String?,
    val dateOfBirth: String?,
    val eyeColour: String?,
    val gender: String?,
    val hairColour: String?,
    val hogwartsStaff: Boolean?,
    val hogwartsStudent: Boolean?,
    val house: String?,
    val image: String?,
    val name: String?,
    val patronus: String?,
    val species: String?,
    val wand: WandAPIModel?,
    val wizard: Boolean?,
    val yearOfBirth: Int?,
) {
    fun toCharacterRoom() = CharacterRoom(
        characterId = id,
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
        name = name ?: "",
        patronus = patronus ?: "",
        species = species ?: "",
        wizard = wizard ?: false,
        yearOfBirth = yearOfBirth ?: 0
    )
}