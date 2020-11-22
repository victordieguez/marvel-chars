package com.android.marvelcharacters.mvp.presenter

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import com.android.marvelcharacters.R
import com.android.marvelcharacters.mvp.interactor.MainInteractor
import com.android.marvelcharacters.mvp.view.MainView
import com.android.marvelcharacters.network.dtos.MarvelCharacter

class MainPresenter(private val mainView: MainView, private val context: Context) {

    private val mainInteractor = MainInteractor(this, context)

    fun searchCharacters(offset: Int) {
        val name = (context as Activity).findViewById<EditText>(R.id.characterNameEditText).text.toString()
        context.findViewById<ProgressBar>(R.id.charactersProgressBar).visibility = View.VISIBLE
        mainInteractor.searchByName(name, offset)
    }

    fun onCharactersSearchSuccess(characters: List<MarvelCharacter>, offset: Int, count: Int, total: Int) {
        mainView.onCharactersSearchSuccess(characters, offset, count, total)
    }

    fun onCharactersSearchFailure() {
        mainView.onCharactersSearchFailure()
    }
}