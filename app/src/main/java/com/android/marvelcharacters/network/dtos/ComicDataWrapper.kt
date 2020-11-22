package com.android.marvelcharacters.network.dtos

class ComicDataWrapper(val data: ComicDataContainer)

class ComicDataContainer(val offset: Int, val total: Int, val count: Int, val results: List<MarvelComic>)

class MarvelComic(val id: Int, val title: String, val description: String, val modified: String, private val thumbnail: MarvelImage) {

    fun getImage(): String {
        return thumbnail.path + "." + thumbnail.extension
    }
}