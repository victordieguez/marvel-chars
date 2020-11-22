package com.android.marvelcharacters.network.dtos

class EventDataWrapper(val data: EventDataContainer)

class EventDataContainer(val offset: Int, val total: Int, val count: Int, val results: List<MarvelEvent>)

class MarvelEvent(val id: Int, val title: String, val description: String, val modified: String, private val thumbnail: MarvelImage) {

    fun getImage(): String {
        return thumbnail.path + "." + thumbnail.extension
    }
}