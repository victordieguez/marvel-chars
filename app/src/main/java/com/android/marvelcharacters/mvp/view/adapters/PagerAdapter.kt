package com.android.marvelcharacters.mvp.view.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.android.marvelcharacters.R
import com.android.marvelcharacters.mvp.view.activities.CharacterTab1Biography

class PagerAdapter(fm: FragmentManager, private val characterId: Long, private val context: Context) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        const val CHARACTER_ACTIVITY_NUM_TABS = 4
    }

    override fun getCount(): Int {
        return CHARACTER_ACTIVITY_NUM_TABS
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return context.getString(R.string.characterTab1Title)
            1 -> return context.getString(R.string.characterTab2Title)
            2 -> return context.getString(R.string.characterTab3Title)
            3 -> return context.getString(R.string.characterTab4Title)
        }
        return null
    }

    override fun getItem(position: Int): Fragment {
        //TODO IR HACIENDO FRAGMENTS
        when (position) {
            0 -> return CharacterTab1Biography.newInstance(characterId)
            1 -> return CharacterTab1Biography.newInstance(characterId)
            2 -> return CharacterTab1Biography.newInstance(characterId)
            3 -> return CharacterTab1Biography.newInstance(characterId)
            //1 -> return CharacterTab2Comics.newInstance(characterId)
            //2 -> return CharacterTab3Series.newInstance(characterId)
            //3 -> return CharacterTab4Events.newInstance(characterId)
        }
        return CharacterTab1Biography.newInstance(characterId)
    }
}