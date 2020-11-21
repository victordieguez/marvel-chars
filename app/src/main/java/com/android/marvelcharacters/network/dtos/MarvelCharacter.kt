package com.android.marvelcharacters.network.dtos

class MarvelCharacter(val id: Int, val name: String, val description: String, val modified: String, private val thumbnail: MarvelCharacterImage) {

    fun getImage(): String {
        return thumbnail.path + "." + thumbnail.extension
    }
}