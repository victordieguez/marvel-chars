package com.android.marvelcharacters.mvp.view

import com.android.marvelcharacters.network.dtos.MarvelCharacter

interface MainView {

    fun onCharactersSearchSuccess(characters: List<MarvelCharacter>, offset:Int, count: Int, total: Int)

    fun onCharactersSearchFailure()

    fun onNextPageCharactersSearchSuccess(characters: List<MarvelCharacter>, offset: Int, count: Int, total: Int)
}