package com.android.marvelcharacters.mvp.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.marvelcharacters.R
import com.android.marvelcharacters.mvp.view.CharacterView
import com.android.marvelcharacters.mvp.view.adapters.PagerAdapter
import kotlinx.android.synthetic.main.activity_character.*

class CharacterActivity : AppCompatActivity(), CharacterView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)
        val characterId = intent.extras?.getLong("characterId")
        title = intent.extras?.getString("name")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (characterId != null) {
            tabLayout.setupWithViewPager(viewPager)
            viewPager.adapter = PagerAdapter(supportFragmentManager, characterId, applicationContext)
        } else {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun showProgressBar() {
    }

    override fun hideProgressBar() {
    }
}