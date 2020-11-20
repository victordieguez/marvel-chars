package com.android.marvelcharacters.mvp.presenter

import android.content.Context
import com.android.marvelcharacters.database.entities.MarvelCharacter
import com.android.marvelcharacters.mvp.interactor.MainInteractor
import com.android.marvelcharacters.mvp.view.MainView

class MainPresenter(private val mainView: MainView, private val context: Context) {

    private val mainInteractor = MainInteractor(this, context)

    fun searchByName(name: String) {
        mainInteractor.searchByName(name)
    }

    fun onCharactersSearchSuccess(characters: List<MarvelCharacter>) {
        mainView.onCharactersSearchSuccess(characters)
    }

    fun onCharactersSearchFailure() {
        mainView.onCharactersSearchFailure()
    }
}