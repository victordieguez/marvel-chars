package com.android.marvelcharacters.mvp.view

import com.android.marvelcharacters.database.entities.MarvelCharacter

interface MainView {

    fun onCharactersSearchSuccess(characters: List<MarvelCharacter>)

    fun onCharactersSearchFailure()
}