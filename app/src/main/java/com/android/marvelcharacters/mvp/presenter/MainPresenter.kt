package com.android.marvelcharacters.mvp.presenter

import android.content.Context
import com.android.marvelcharacters.mvp.interactor.MainInteractor
import com.android.marvelcharacters.mvp.view.MainView
import com.android.marvelcharacters.network.dtos.MarvelCharacter

class MainPresenter(private val mainView: MainView, private val context: Context) {

    private val mainInteractor = MainInteractor(this, context)

    fun searchCharacters(name: String, offset: Int) {
        mainView.showProgressBar()
        mainInteractor.searchCharacters(name, offset)
    }

    fun onCharactersSearchSuccess(characters: List<MarvelCharacter>, offset: Int, count: Int, total: Int) {
        mainView.hideProgressBar()
        mainView.onCharactersSearchSuccess(characters, offset, count, total)
    }

    fun onCharactersSearchFailure() {
        mainView.hideProgressBar()
        mainView.onCharactersSearchFailure()
    }
}