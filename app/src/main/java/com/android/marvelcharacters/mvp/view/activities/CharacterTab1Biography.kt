package com.android.marvelcharacters.mvp.view.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.marvelcharacters.R
import com.android.marvelcharacters.mvp.presenter.CharacterPresenter
import com.android.marvelcharacters.mvp.view.CharacterView
import com.android.marvelcharacters.network.dtos.MarvelCharacter

class CharacterTab1Biography : Fragment(), CharacterView {

    private var characterPresenter: CharacterPresenter? = null
    private lateinit var rootView: View

    companion object {
        fun newInstance(characterId: Long): CharacterTab1Biography {
            val args = Bundle()
            val fragment = CharacterTab1Biography()
            args.putLong("characterId", characterId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val driverId = arguments?.getLong("characterId")
        rootView = inflater.inflate(R.layout.character_tab1, container, false)
        if (context != null && driverId != null) {
            characterPresenter = CharacterPresenter(this, context!!)
            /*val character = characterPresenter?.getCharacter(driverId)
            if (character != null) {
                fillData(character)
            }*/
        }
        rootView.findViewById<TextView>(R.id.nameTextView).text = "PATATA"
        return rootView
    }

    private fun fillData(character: MarvelCharacter) {
        rootView.findViewById<TextView>(R.id.nameTextView).text = "PATATA"
    }
}