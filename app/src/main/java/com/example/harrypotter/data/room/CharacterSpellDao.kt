package com.example.harrypotter.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.harrypotter.data.local.CharacterRoom
import com.example.harrypotter.data.local.CharacterSpellCross
import com.example.harrypotter.data.local.CharacterWithSpells
import com.example.harrypotter.data.local.SpellRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterSpellDao {

    @Query("SELECT * FROM CharacterRoom")
    suspend fun getAllCharacters(): List<CharacterRoom>

    @Query("SELECT * FROM SpellRoom")
    suspend fun getAllSpells(): List<SpellRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(items: List<CharacterRoom>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSpells(items: List<SpellRoom>)

    @Query("SELECT * FROM CharacterRoom")
    fun listenToAllCharacters(): Flow<List<CharacterRoom>>

    @Query("SELECT * FROM CharacterRoom WHERE house = :house")
    fun listenToCharactersByHouse(house: String): Flow<List<CharacterRoom>>

    @Update
    suspend fun updateCharacterHouse(characterRoom: CharacterRoom)

    @Query("SELECT * FROM CharacterRoom WHERE characterId = :id")
    fun listenToCharacterWithSpell(id: String): Flow<CharacterWithSpells>

    @Transaction
    suspend fun updateCharacterWithSpell(characterId: String, newSpells: List<String>) {
        newSpells.forEach { spellId ->
            insertCharacterSpellCrossRef(CharacterSpellCross(characterId, spellId))
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterSpellCrossRef(crossRef: CharacterSpellCross)
}