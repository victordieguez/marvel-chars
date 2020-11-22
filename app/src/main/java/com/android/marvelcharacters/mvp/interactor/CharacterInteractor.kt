package com.android.marvelcharacters.mvp.interactor

import android.content.Context
import com.android.marvelcharacters.mvp.presenter.CharacterPresenter
import com.android.marvelcharacters.network.NetworkService
import com.android.marvelcharacters.network.NetworkURL
import com.android.marvelcharacters.network.dtos.CharacterDataWrapper
import com.android.marvelcharacters.network.dtos.ComicDataWrapper
import com.android.marvelcharacters.network.dtos.EventDataWrapper
import com.android.marvelcharacters.network.dtos.SeriesDataWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterInteractor(private val characterPresenter: CharacterPresenter, private val context: Context) {

    companion object {
        const val MAX_LIMIT = 100
    }

    fun searchCharacter(id: Long) {
        val request = NetworkService.buildService(NetworkURL::class.java, context)
        val call = request.getCharacter(id)
        call.enqueue(object : Callback<CharacterDataWrapper> {
            override fun onResponse(call: Call<CharacterDataWrapper>, response: Response<CharacterDataWrapper>) {
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()!!.data
                    if (data.results.isNotEmpty()) {
                        characterPresenter.onCharacterSearchSuccess(data.results[0])
                    } else {
                        characterPresenter.onCharacterSearchFailure()
                    }
                } else {
                    characterPresenter.onCharacterSearchFailure()
                }
            }

            override fun onFailure(call: Call<CharacterDataWrapper>, t: Throwable) {
                characterPresenter.onCharacterSearchFailure()
            }
        })
    }

    fun searchComics(id: Long, offset: Int) {
        val request = NetworkService.buildService(NetworkURL::class.java, context)
        val call = request.getCharacterComics(id, offset, MAX_LIMIT)
        call.enqueue(object : Callback<ComicDataWrapper> {
            override fun onResponse(call: Call<ComicDataWrapper>, response: Response<ComicDataWrapper>) {
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()!!.data
                    characterPresenter.onComicsSearchSuccess(id, data.results, data.offset, data.count, data.total)
                } else {
                    characterPresenter.onComicsSearchFailure()
                }
            }

            override fun onFailure(call: Call<ComicDataWrapper>, t: Throwable) {
                characterPresenter.onComicsSearchFailure()
            }
        })
    }

    fun searchSeries(id: Long, offset: Int) {
        val request = NetworkService.buildService(NetworkURL::class.java, context)
        val call = request.getCharacterSeries(id, offset, MAX_LIMIT)
        call.enqueue(object : Callback<SeriesDataWrapper> {
            override fun onResponse(call: Call<SeriesDataWrapper>, response: Response<SeriesDataWrapper>) {
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()!!.data
                    characterPresenter.onSeriesSearchSuccess(id, data.results, data.offset, data.count, data.total)
                } else {
                    characterPresenter.onSeriesSearchFailure()
                }
            }

            override fun onFailure(call: Call<SeriesDataWrapper>, t: Throwable) {
                characterPresenter.onSeriesSearchFailure()
            }
        })
    }

    fun searchEvents(id: Long, offset: Int) {
        val request = NetworkService.buildService(NetworkURL::class.java, context)
        val call = request.getCharacterEvents(id, offset, MAX_LIMIT)
        call.enqueue(object : Callback<EventDataWrapper> {
            override fun onResponse(call: Call<EventDataWrapper>, response: Response<EventDataWrapper>) {
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()!!.data
                    characterPresenter.onEventsSearchSuccess(id, data.results, data.offset, data.count, data.total)
                } else {
                    characterPresenter.onEventsSearchFailure()
                }
            }

            override fun onFailure(call: Call<EventDataWrapper>, t: Throwable) {
                characterPresenter.onEventsSearchFailure()
            }
        })
    }
}