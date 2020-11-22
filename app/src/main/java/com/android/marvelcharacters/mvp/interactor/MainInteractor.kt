package com.android.marvelcharacters.mvp.interactor

import android.content.Context
import com.android.marvelcharacters.mvp.presenter.MainPresenter
import com.android.marvelcharacters.network.NetworkService
import com.android.marvelcharacters.network.NetworkURL
import com.android.marvelcharacters.network.dtos.CharacterDataWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainInteractor(private val mainPresenter: MainPresenter, private val context: Context) {

    fun searchCharacters(name: String, offset: Int) {
        val request = NetworkService.buildService(NetworkURL::class.java, context)
        val call = if (name.isEmpty()) {
            request.getCharacters(offset)
        } else {
            request.getCharactersByNameStart(name, offset)
        }
        call.enqueue(object : Callback<CharacterDataWrapper> {
            override fun onResponse(call: Call<CharacterDataWrapper>, response: Response<CharacterDataWrapper>) {
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()!!.data
                    mainPresenter.onCharactersSearchSuccess(data.results, data.offset, data.count, data.total)
                } else {
                    mainPresenter.onCharactersSearchFailure()
                }
            }

            override fun onFailure(call: Call<CharacterDataWrapper>, t: Throwable) {
                mainPresenter.onCharactersSearchFailure()
            }
        })
    }
}