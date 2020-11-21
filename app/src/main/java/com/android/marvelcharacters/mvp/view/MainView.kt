package com.android.marvelcharacters.mvp.view

import com.android.marvelcharacters.network.dtos.MarvelCharacter

interface MainView {

    fun onCharactersSearchSuccess(characters: List<MarvelCharacter>)

    fun onCharactersSearchFailure()
}