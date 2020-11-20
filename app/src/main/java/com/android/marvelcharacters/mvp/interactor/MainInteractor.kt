package com.android.marvelcharacters.mvp.interactor

import android.content.Context
import com.android.marvelcharacters.database.entities.MarvelCharacter
import com.android.marvelcharacters.mvp.presenter.MainPresenter
import java.util.Date

class MainInteractor(private val mainPresenter: MainPresenter, private val context: Context) {

    fun searchByName(name: String) {
        //TODO HACER LLAMADA A WS
        val characters = mutableListOf<MarvelCharacter>()
        characters.add(MarvelCharacter(1,"NAME 1", "DESCR 1", Date(), "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"))
        characters.add(MarvelCharacter(2,"NAME 2", "DESCR 2", Date(), "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg"))
        characters.add(MarvelCharacter(4,"NAME 3", "DESCR 3", Date(), "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg"))
        characters.add(MarvelCharacter(4,"NAME 4", "DESCR 4", Date(), "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg"))
        characters.add(MarvelCharacter(5,"NAME 5", "DESCR 5", Date(), "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg"))
        mainPresenter.onCharactersSearchSuccess(characters)
    }
}