package com.example.harrypotter.data.network

import retrofit2.Response
import retrofit2.http.GET
import com.example.harrypotter.util.Utils.GET_CHARACTERS
import com.example.harrypotter.data.model.CharacterAPIModel
import com.example.harrypotter.data.model.SpellAPIModel
import retrofit2.http.Path
import com.example.harrypotter.util.Utils.GET_CHARACTER
import com.example.harrypotter.util.Utils.GET_SPELLS

interface ApiService {
    @GET(GET_CHARACTERS)
    suspend fun getCharacters(): Response<List<CharacterAPIModel>>

    @GET("${GET_CHARACTER}{id}")
    suspend fun getCharacterByID(@Path("id") id: String): Response<List<CharacterAPIModel>>

    @GET(GET_SPELLS)
    suspend fun getSpells(): Response<List<SpellAPIModel>>
}