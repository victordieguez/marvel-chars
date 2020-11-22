package com.android.marvelcharacters.mvp.presenter

import android.content.Context
import com.android.marvelcharacters.mvp.interactor.CharacterInteractor
import com.android.marvelcharacters.mvp.view.CharacterView
import com.android.marvelcharacters.mvp.view.activities.CharacterTab1Biography
import com.android.marvelcharacters.mvp.view.activities.CharacterTab2Comics
import com.android.marvelcharacters.network.dtos.MarvelCharacter
import com.android.marvelcharacters.network.dtos.MarvelComic

class CharacterPresenter(private val characterView: CharacterView, private val context: Context) {

    private val characterInteractor = CharacterInteractor(this, context)

    fun searchCharacter(id: Long) {
        characterInteractor.searchCharacter(id)
    }

    fun searchComics(id: Long, offset: Int) {
        characterInteractor.searchComics(id, offset)
    }

    fun onCharacterSearchSuccess(character: MarvelCharacter) {
        (characterView as CharacterTab1Biography).onCharacterSearchSuccess(character)
    }

    fun onCharacterSearchFailure() {
        (characterView as CharacterTab1Biography).onCharacterSearchFailure()
    }

    fun onComicsSearchSuccess(characterId: Long, comics: List<MarvelComic>, offset: Int, count: Int, total: Int) {
        (characterView as CharacterTab2Comics).onComicsSearchSuccess(characterId, comics, offset, count, total)
    }

    fun onComicsSearchFailure() {
        (characterView as CharacterTab2Comics).onComicsSearchFailure()
    }
}