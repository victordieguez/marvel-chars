package com.android.marvelcharacters.mvp.view.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.marvelcharacters.R
import com.android.marvelcharacters.network.dtos.MarvelCharacter
import com.android.marvelcharacters.mvp.presenter.MainPresenter
import com.android.marvelcharacters.mvp.view.MainView
import com.android.marvelcharacters.mvp.view.adapters.CharactersRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private var mainPresenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = MainPresenter(this, this)

        mainPresenter?.searchByName("")
        characterNameEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                mainPresenter?.searchByName(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
        })
    }

    override fun onCharactersSearchSuccess(characters: List<MarvelCharacter>) {
        charactersRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        charactersRecyclerView.adapter = CharactersRecyclerViewAdapter(characters)
    }

    override fun onCharactersSearchFailure() {
        Toast.makeText(this, "Error loading characters", Toast.LENGTH_SHORT).show()
    }
}
