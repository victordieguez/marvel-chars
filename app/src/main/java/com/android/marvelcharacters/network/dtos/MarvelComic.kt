package com.android.marvelcharacters.network.dtos

class MarvelComic(val id: Int, val title: String, val description: String, val modified: String, private val thumbnail: MarvelCharacterImage) {

    fun getImage(): String {
        return thumbnail.path + "." + thumbnail.extension
    }
}