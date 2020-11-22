package com.android.marvelcharacters.mvp.presenter

import android.content.Context
import com.android.marvelcharacters.mvp.interactor.CharacterInteractor
import com.android.marvelcharacters.mvp.view.CharacterView

class CharacterPresenter(private val characterView: CharacterView, private val context: Context) {

    private val characterInteractor = CharacterInteractor(this, context)
}