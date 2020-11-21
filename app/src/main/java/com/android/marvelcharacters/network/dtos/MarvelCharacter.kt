package com.android.marvelcharacters.network.dtos

import java.util.Date

class MarvelCharacter(val id: Int, val name: String, val description: String, val modified: Date, private val thumbnail: MarvelCharacterImage) {

    fun getImage(): String {
        return thumbnail.path + "." + thumbnail.extension
    }
}