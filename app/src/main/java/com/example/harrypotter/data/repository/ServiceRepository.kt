package com.example.harrypotter.data.repository

import com.example.harrypotter.data.network.ApiService
import com.example.harrypotter.data.room.CharacterSpellDao
import com.example.harrypotter.presentation.ui.models.Character
import com.example.harrypotter.presentation.ui.models.Spell
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class ServiceRepository @Inject constructor(
    private val api: ApiService,
    private val dao: CharacterSpellDao
) {
    suspend fun teachSpell(characterId: String, spellId: String) {
        dao.updateCharacterWithSpell(characterId, listOf(spellId))
    }

    suspend fun changeCharacterHouse(character: Character, house: String) {
        dao.updateCharacterHouse(
            characterRoom = character.toCharacterRoom().apply { this.house = house })
    }

    fun listenToAllCharacters(): Flow<List<Character>> {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        scope.launch {
            val allLocalCharacters = dao.getAllCharacters()
            if (allLocalCharacters.isEmpty()) {
                val downloadCharacters = api.getCharacters().body()
                if (downloadCharacters != null) {
                    dao.insertAllCharacters(downloadCharacters.map { it.toCharacterRoom() })
                }
            }
        }
        return dao.listenToAllCharacters().map { list ->
            list.map { it.toCharacter() }
        }
    }

    fun listenToAllCharactersByHouse(house: String): Flow<List<Character>> {
        return dao.listenToCharactersByHouse(house).map { list ->
            list.map {
                it.toCharacter()
            }
        }
    }

    fun listenToCharacter(id: String): Flow<Character> =
        dao.listenToCharacterWithSpell(id).map { it.toCharacter() }

    suspend fun getSpells(): List<Spell> {
        val localSpells = dao.getAllSpells()
        return if (localSpells.isEmpty()) {
            val spells = api.getSpells().body()?.map { it.toSpellRoom() }
            if (spells != null) {
                dao.insertAllSpells(spells)
            }
            spells?.map { it.toSpell() }.orEmpty()
        } else {
            localSpells.map { it.toSpell() }
        }
    }
}