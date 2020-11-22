package com.android.marvelcharacters.mvp.view.activities

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.marvelcharacters.R
import com.android.marvelcharacters.mvp.presenter.MainPresenter
import com.android.marvelcharacters.mvp.view.MainView
import com.android.marvelcharacters.mvp.view.adapters.CharactersRecyclerViewAdapter
import com.android.marvelcharacters.mvp.view.adapters.CharactersRecyclerViewOnScrollListener
import com.android.marvelcharacters.network.dtos.MarvelCharacter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private var mainPresenter: MainPresenter? = null
    private var adapter: CharactersRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = MainPresenter(this, this)

        adapter = CharactersRecyclerViewAdapter()
        charactersRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        charactersRecyclerView.adapter = adapter

        mainPresenter?.searchCharacters(characterNameEditText.text.toString(), 0)
        searchImageButton.setOnClickListener {
            mainPresenter?.searchCharacters(characterNameEditText.text.toString(), 0)
        }
    }

    override fun onCharactersSearchSuccess(characters: List<MarvelCharacter>, offset: Int, count: Int, total: Int) {
        if (offset == 0) {
            adapter?.setCharacters(characters)
        } else {
            adapter?.addCharacters(characters)
        }
        adapter?.notifyDataSetChanged()
        charactersRecyclerView.clearOnScrollListeners()
        if (mainPresenter != null) {
            charactersRecyclerView.addOnScrollListener(CharactersRecyclerViewOnScrollListener(mainPresenter!!, characterNameEditText.text.toString(), offset, count, total))
        }
        charactersProgressBar.visibility = View.GONE
        hideKeyboard()
    }

    override fun onCharactersSearchFailure() {
        charactersRecyclerView.adapter = CharactersRecyclerViewAdapter()
        charactersProgressBar.visibility = View.GONE
        Toast.makeText(this, getString(R.string.characters_load_error), Toast.LENGTH_SHORT).show()
        hideKeyboard()
    }

    private fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}