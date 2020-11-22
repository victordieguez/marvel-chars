package com.android.marvelcharacters.network.dtos

class CharacterDataContainer(val offset: Int, val total: Int, val count: Int, val results: List<MarvelCharacter>)