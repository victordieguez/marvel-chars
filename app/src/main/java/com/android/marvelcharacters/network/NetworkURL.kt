package com.android.marvelcharacters.network

import com.android.marvelcharacters.network.dtos.CharacterDataWrapper
import com.android.marvelcharacters.network.dtos.ComicDataWrapper
import com.android.marvelcharacters.network.dtos.EventDataWrapper
import com.android.marvelcharacters.network.dtos.SeriesDataWrapper
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

    @GET("characters/{characterId}/comics")
    fun getCharacterComics(@Path("characterId") characterId: Long, @Query("offset") offset: Int, @Query("limit") limit: Int): Call<ComicDataWrapper>

    @GET("characters/{characterId}/series")
    fun getCharacterSeries(@Path("characterId") characterId: Long, @Query("offset") offset: Int, @Query("limit") limit: Int): Call<SeriesDataWrapper>

    @GET("characters/{characterId}/events")
    fun getCharacterEvents(@Path("characterId") characterId: Long, @Query("offset") offset: Int, @Query("limit") limit: Int): Call<EventDataWrapper>
}