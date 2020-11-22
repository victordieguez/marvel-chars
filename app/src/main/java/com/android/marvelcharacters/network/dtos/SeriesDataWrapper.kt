package com.android.marvelcharacters.network.dtos

class SeriesDataWrapper(val data: SeriesDataContainer)

class SeriesDataContainer(val offset: Int, val total: Int, val count: Int, val results: List<MarvelSeries>)

class MarvelSeries(val id: Int, val title: String, val description: String, val modified: String, private val thumbnail: MarvelImage) {

    fun getImage(): String {
        return thumbnail.path + "." + thumbnail.extension
    }
}