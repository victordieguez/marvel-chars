package com.android.marvelcharacters.network

import com.android.marvelcharacters.network.dtos.CharacterDataWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkURL {
    companion object {
        const val BASE_URL = "https://gateway.marvel.com:443/v1/public/"
    }

    @GET("characters")
    fun getCharacters(@Query("offset") offset: Int): Call<CharacterDataWrapper>

    @GET("characters")
    fun getCharactersByNameStart(@Query("nameStartsWith") name: String, @Query("offset") offset: Int): Call<CharacterDataWrapper>

    @GET("characters/{characterId}")
    fun getCharacter(@Path("characterId") characterId: Long): Call<CharacterDataWrapper>
}