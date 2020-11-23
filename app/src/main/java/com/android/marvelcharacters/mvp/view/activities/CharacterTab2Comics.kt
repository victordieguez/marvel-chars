package com.android.marvelcharacters.mvp.view.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.marvelcharacters.R
import com.android.marvelcharacters.mvp.presenter.CharacterPresenter
import com.android.marvelcharacters.mvp.view.CharacterView
import com.android.marvelcharacters.mvp.view.adapters.ComicsRecyclerViewAdapter
import com.android.marvelcharacters.mvp.view.adapters.ComicsRecyclerViewOnScrollListener
import com.android.marvelcharacters.network.dtos.MarvelComic

class CharacterTab2Comics : Fragment(), CharacterView {

    private lateinit var rootView: View
    private var characterPresenter: CharacterPresenter? = null
    private var adapter: ComicsRecyclerViewAdapter? = null

    companion object {
        fun newInstance(characterId: Long): CharacterTab2Comics {
            val args = Bundle()
            val fragment = CharacterTab2Comics()
            args.putLong("characterId", characterId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val characterId = arguments?.getLong("characterId")
        rootView = inflater.inflate(R.layout.character_tab2, container, false)
        if (context != null && characterId != null) {
            characterPresenter = CharacterPresenter(this, context!!)

            adapter = ComicsRecyclerViewAdapter()
            val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.layoutManager = GridLayoutManager(rootView.context, 2)
            recyclerView.adapter = adapter
            characterPresenter?.searchComics(characterId, 0)
        }
        return rootView
    }

    /**
     * Listener called when any comics search returns data successfully
     * @param comics New comics loaded from the API to be added to the list
     * @param offset Number used to identify the position of the first comic of this list in the total amount (if it's 0 we are on the first page, if not the second page has been loaded)
     * @param count Number of comics returned from the API
     * @param total Total amount of comics that match with the current query
     */
    fun onComicsSearchSuccess(characterId: Long, comics: List<MarvelComic>, offset: Int, count: Int, total: Int) {
        if (offset == 0) {
            adapter?.setComics(comics)
        } else {
            adapter?.addComics(comics)
        }
        adapter?.notifyDataSetChanged()

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.clearOnScrollListeners()
        if (characterPresenter != null) {
            recyclerView.addOnScrollListener(ComicsRecyclerViewOnScrollListener(characterPresenter!!, characterId, offset, count, total))
        }
    }

    /**
     * Listener called in case of any error in comics search
     */
    fun onComicsSearchFailure() {
        rootView.findViewById<RecyclerView>(R.id.recyclerView).adapter = ComicsRecyclerViewAdapter()
        Toast.makeText(rootView.context, getString(R.string.comics_load_error), Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        rootView.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        rootView.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
    }
}