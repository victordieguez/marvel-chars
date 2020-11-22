package com.android.marvelcharacters.mvp.presenter

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ProgressBar
import com.android.marvelcharacters.R
import com.android.marvelcharacters.mvp.interactor.CharacterInteractor
import com.android.marvelcharacters.mvp.view.CharacterView
import com.android.marvelcharacters.mvp.view.activities.CharacterTab1Biography
import com.android.marvelcharacters.mvp.view.activities.CharacterTab2Comics
import com.android.marvelcharacters.mvp.view.activities.CharacterTab3Series
import com.android.marvelcharacters.mvp.view.activities.CharacterTab4Events
import com.android.marvelcharacters.network.dtos.MarvelCharacter
import com.android.marvelcharacters.network.dtos.MarvelComic
import com.android.marvelcharacters.network.dtos.MarvelEvent
import com.android.marvelcharacters.network.dtos.MarvelSeries

class CharacterPresenter(private val characterView: CharacterView, private val context: Context) {

    private val characterInteractor = CharacterInteractor(this, context)

    fun searchCharacter(id: Long) {
        (context as Activity).findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
        characterInteractor.searchCharacter(id)
    }

    fun searchComics(id: Long, offset: Int) {
        (context as Activity).findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
        characterInteractor.searchComics(id, offset)
    }

    fun searchSeries(id: Long, offset: Int) {
        (context as Activity).findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
        characterInteractor.searchSeries(id, offset)
    }

    fun searchEvents(id: Long, offset: Int) {
        (context as Activity).findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
        characterInteractor.searchEvents(id, offset)
    }

    fun onCharacterSearchSuccess(character: MarvelCharacter) {
        (context as Activity).findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
        (characterView as CharacterTab1Biography).onCharacterSearchSuccess(character)
    }

    fun onCharacterSearchFailure() {
        (context as Activity).findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
        (characterView as CharacterTab1Biography).onCharacterSearchFailure()
    }

    fun onComicsSearchSuccess(characterId: Long, comics: List<MarvelComic>, offset: Int, count: Int, total: Int) {
        (context as Activity).findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
        (characterView as CharacterTab2Comics).onComicsSearchSuccess(characterId, comics, offset, count, total)
    }

    fun onComicsSearchFailure() {
        (context as Activity).findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
        (characterView as CharacterTab2Comics).onComicsSearchFailure()
    }

    fun onSeriesSearchSuccess(characterId: Long, series: List<MarvelSeries>, offset: Int, count: Int, total: Int) {
        (context as Activity).findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
        (characterView as CharacterTab3Series).onSeriesSearchSuccess(characterId, series, offset, count, total)
    }

    fun onSeriesSearchFailure() {
        (context as Activity).findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
        (characterView as CharacterTab3Series).onSeriesSearchFailure()
    }

    fun onEventsSearchSuccess(characterId: Long, events: List<MarvelEvent>, offset: Int, count: Int, total: Int) {
        (context as Activity).findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
        (characterView as CharacterTab4Events).onEventsSearchSuccess(characterId, events, offset, count, total)
    }

    fun onEventsSearchFailure() {
        (context as Activity).findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
        (characterView as CharacterTab4Events).onEventsSearchFailure()
    }
}