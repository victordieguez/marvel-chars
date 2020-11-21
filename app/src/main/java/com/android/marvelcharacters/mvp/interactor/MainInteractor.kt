package com.android.marvelcharacters.mvp.interactor

import android.content.Context
import com.android.marvelcharacters.R
import com.android.marvelcharacters.mvp.presenter.MainPresenter
import com.android.marvelcharacters.network.NetworkService
import com.android.marvelcharacters.network.NetworkURL
import com.android.marvelcharacters.network.dtos.CharacterDataWrapper
import okhttp3.internal.and
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainInteractor(private val mainPresenter: MainPresenter, private val context: Context) {

    fun searchByName(name: String) {
        val time = System.currentTimeMillis()
        val hash = generateHash(time.toString(), context.getString(R.string.public_api_key), context.getString(R.string.private_api_key))
        val request = NetworkService.buildService(NetworkURL::class.java)
        val call = request.getCharacters(context.getString(R.string.public_api_key), time, hash)
        call.enqueue(object : Callback<CharacterDataWrapper> {
            override fun onResponse(call: Call<CharacterDataWrapper>, response: Response<CharacterDataWrapper>) {
                if (response.isSuccessful && response.body() != null) {
                    mainPresenter.onCharactersSearchSuccess(response.body()!!.data.results)
                } else {
                    mainPresenter.onCharactersSearchFailure()
                }
            }

            override fun onFailure(call: Call<CharacterDataWrapper>, t: Throwable) {
                mainPresenter.onCharactersSearchFailure()
            }
        })
    }

    private fun generateHash(timestamp: String, publicKey: String, privateKey: String): String {
        return try {
            val value = timestamp + privateKey + publicKey
            val md5Encoder = MessageDigest.getInstance("MD5")
            val md5Bytes: ByteArray = md5Encoder.digest(value.toByteArray())
            val md5 = StringBuilder()
            for (i in md5Bytes.indices) {
                md5.append(Integer.toHexString(md5Bytes[i] and 0xFF or 0x100).substring(1, 3))
            }
            md5.toString()
        } catch (e: NoSuchAlgorithmException) {
            return ""
            //TODO HACER ALGO CON ESTE ERROR DE HASH: ¿EXCEPCIONES?
        }
    }
}