package com.android.marvelcharacters.mvp.view.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.marvelcharacters.R
import com.android.marvelcharacters.mvp.presenter.CharacterPresenter
import com.android.marvelcharacters.mvp.view.CharacterView
import com.android.marvelcharacters.network.dtos.MarvelCharacter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class CharacterTab1Biography : Fragment(), CharacterView {

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
        val characterId = arguments?.getLong("characterId")
        rootView = inflater.inflate(R.layout.character_tab1, container, false)
        if (context != null && characterId != null) {
            val characterPresenter = CharacterPresenter(this, context!!)
            characterPresenter.searchCharacter(characterId)
        }
        return rootView
    }

    /**
     * Listener called when a character search returns data successfully
     * @param character Character loaded from the API
     */
    fun onCharacterSearchSuccess(character: MarvelCharacter) {
        Glide.with(rootView.context).load(character.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).into(rootView.findViewById(R.id.characterImageView))
        rootView.findViewById<TextView>(R.id.descriptionTextView).text = character.description

        try {
            val ldt = LocalDateTime.parse(character.modified, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ"))
            val currentZoneId = ZoneId.systemDefault()
            val currentZonedDateTime = ldt.atZone(currentZoneId)
            val format = DateTimeFormatter.ofPattern("dd/MM/yyyy")

            val dateString = String.format(context!!.resources.getString(R.string.lastUpdate), format.format(currentZonedDateTime))
            rootView.findViewById<TextView>(R.id.lastModificationTextView).text = dateString
        } catch (ignored: Exception) {
            //Some dates can come with wrong format with negative value
        }
    }

    /**
     * Listener called in case of any error in character search
     */
    fun onCharacterSearchFailure() {
        Toast.makeText(rootView.context, getString(R.string.character_load_error), Toast.LENGTH_SHORT).show()
    }
}