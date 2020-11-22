package com.android.marvelcharacters.mvp.presenter

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ProgressBar
import com.android.marvelcharacters.R
import com.android.marvelcharacters.mvp.interactor.MainInteractor
import com.android.marvelcharacters.mvp.view.MainView
import com.android.marvelcharacters.network.dtos.MarvelCharacter

class MainPresenter(private val mainView: MainView, private val context: Context) {

    private val mainInteractor = MainInteractor(this, context)

    fun searchCharacters(name: String, offset: Int) {
        (context as Activity).findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
        mainInteractor.searchCharacters(name, offset)
    }

    fun onCharactersSearchSuccess(characters: List<MarvelCharacter>, offset: Int, count: Int, total: Int) {
        mainView.onCharactersSearchSuccess(characters, offset, count, total)
    }

    fun onCharactersSearchFailure() {
        mainView.onCharactersSearchFailure()
    }
}