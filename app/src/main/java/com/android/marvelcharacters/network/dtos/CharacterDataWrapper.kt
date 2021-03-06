package com.android.marvelcharacters.network.dtos

class CharacterDataWrapper(val data: CharacterDataContainer)

class CharacterDataContainer(val offset: Int, val total: Int, val count: Int, val results: List<MarvelCharacter>)

class MarvelCharacter(val id: Int, val name: String, val description: String, val modified: String, private val thumbnail: MarvelImage, private val urls: List<MarvelUrl>) {

    fun getImage(): String {
        return thumbnail.path + "." + thumbnail.extension
    }

    fun getUrl(type: String): String {
        urls.forEach {
            if (it.type == type) {
                return it.url
            }
        }
        return ""
    }
}

class MarvelUrl(val type: String, val url: String)