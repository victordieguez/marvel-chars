package com.android.marvelcharacters.mvp.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.marvelcharacters.R
import com.android.marvelcharacters.mvp.presenter.CharacterPresenter
import com.android.marvelcharacters.mvp.view.CharacterView

class CharacterActivity : AppCompatActivity(), CharacterView {

    private var characterPresenter: CharacterPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        val characterId = intent.extras?.getLong("characterId")
        characterPresenter = CharacterPresenter(this, this)
    }
}