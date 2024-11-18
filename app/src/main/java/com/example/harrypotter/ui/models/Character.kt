package com.example.harrypotter.ui.models

import android.os.Parcel
import android.os.Parcelable
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
) : Parcelable {

    constructor(parcel: Parcel) : this(
        characterId = parcel.readString() ?: "",
        actor = parcel.readString(),
        alive = parcel.readValue(Boolean::class.java.classLoader) as? Boolean ?: false,
        ancestry = parcel.readString(),
        dateOfBirth = parcel.readString(),
        eyeColour = parcel.readString(),
        gender = parcel.readString(),
        hairColour = parcel.readString(),
        hogwartsStaff = parcel.readValue(Boolean::class.java.classLoader) as? Boolean ?: false,
        hogwartsStudent = parcel.readValue(Boolean::class.java.classLoader) as? Boolean ?: false,
        house = parcel.readString(),
        image = parcel.readString(),
        patronus = parcel.readString(),
        species = parcel.readString(),
        wizard = parcel.readValue(Boolean::class.java.classLoader) as? Boolean ?: false,
        yearOfBirth = parcel.readValue(Int::class.java.classLoader) as? Int ?: 0,
        wandWood = parcel.readString(),
        wandCore = parcel.readString(),
        wandLength = parcel.readValue(Double::class.java.classLoader) as? Double ?: 0.0,
        name = parcel.readString(),
        spells = parcel.createTypedArrayList(Spell.CREATOR) ?: listOf()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(characterId)
        parcel.writeString(actor)
        parcel.writeValue(alive)
        parcel.writeString(ancestry)
        parcel.writeString(dateOfBirth)
        parcel.writeString(eyeColour)
        parcel.writeString(gender)
        parcel.writeString(hairColour)
        parcel.writeValue(hogwartsStaff)
        parcel.writeValue(hogwartsStudent)
        parcel.writeString(house)
        parcel.writeString(image)
        parcel.writeString(patronus)
        parcel.writeString(species)
        parcel.writeValue(wizard)
        parcel.writeValue(yearOfBirth)
        parcel.writeString(wandWood)
        parcel.writeString(wandCore)
        parcel.writeValue(wandLength)
        parcel.writeString(wandWood)
        parcel.writeString(name)
        parcel.writeTypedList(spells)
    }

    override fun describeContents(): Int = 0

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Character> {
            override fun createFromParcel(parcel: Parcel): Character {
                return Character(parcel)
            }

            override fun newArray(size: Int): Array<Character?> {
                return arrayOfNulls(size)
            }
        }
    }

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
