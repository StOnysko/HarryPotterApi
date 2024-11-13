package com.example.harrypotter.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.harrypotter.data.local.CharacterRoom
import com.example.harrypotter.data.local.CharacterSpellCross
import com.example.harrypotter.data.local.SpellRoom

@Database(
    entities = [CharacterRoom::class, SpellRoom::class, CharacterSpellCross::class],
    version = 2,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterSpellDao
}