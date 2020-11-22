package com.android.marvelcharacters.mvp.interactor

import android.content.Context
import com.android.marvelcharacters.mvp.presenter.CharacterPresenter
import com.android.marvelcharacters.network.NetworkService
import com.android.marvelcharacters.network.NetworkURL
import com.android.marvelcharacters.network.dtos.CharacterDataWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterInteractor(private val characterPresenter: CharacterPresenter, private val context: Context) {

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
}