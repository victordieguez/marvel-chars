package com.android.marvelcharacters.mvp.view

import com.android.marvelcharacters.network.dtos.MarvelCharacter

interface MainView {

    fun showProgressBar()

    fun hideProgressBar()

    /**
     * Listener called when any characters search returns data successfully
     * @param characters New characters loaded from the API to be added to the list
     * @param offset Number used to identify the position of the first character of this list in the total amount (if it's 0 we are on the first page, if not the second page has been loaded)
     * @param count Number of characters returned from the API
     * @param total Total amount of characters that match with the current query
     */
    fun onCharactersSearchSuccess(characters: List<MarvelCharacter>, offset: Int, count: Int, total: Int)

    /**
     * Listener called in case of any error in characters search
     */
    fun onCharactersSearchFailure()
}