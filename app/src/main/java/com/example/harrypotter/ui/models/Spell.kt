package com.example.harrypotter.ui.models

import android.os.Parcel
import android.os.Parcelable

data class Spell(
    val id: String,
    val description: String,
    val name: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        id = parcel.readString() ?: "",
        description = parcel.readString() ?: "",
        name = parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(description)
        parcel.writeString(name)
    }

    override fun describeContents(): Int = 0

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Spell> {
            override fun createFromParcel(parcel: Parcel): Spell {
                return Spell(parcel)
            }

            override fun newArray(size: Int): Array<Spell?> {
                return arrayOfNulls(size)
            }
        }
    }
}