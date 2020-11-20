package com.android.marvelcharacters.mvp.view.activities

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.marvelcharacters.R
import com.android.marvelcharacters.database.entities.MarvelCharacter
import com.android.marvelcharacters.mvp.presenter.MainPresenter
import com.android.marvelcharacters.mvp.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

class MainActivity : AppCompatActivity(), MainView {

    private var mainPresenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = MainPresenter(this, this)

        mainPresenter?.searchByName("")
        characterNameEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                mainPresenter?.searchByName(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
        })
    }

    override fun onCharactersSearchSuccess(characters: List<MarvelCharacter>) {
        charactersRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        charactersRecyclerView.adapter = CharactersRecyclerViewAdapter(characters)
    }

    override fun onCharactersSearchFailure() {
        Toast.makeText(this, "Error loading characters", Toast.LENGTH_SHORT).show()
    }
}

private class CharactersRecyclerViewAdapter(private val characters: List<MarvelCharacter>) : RecyclerView.Adapter<CharactersRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_layout, parent, false)
        return ViewHolder(viewHolder);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        DownLoadImageTask(holder.image).execute(characters[position].thumbnail)
        holder.name.text = characters[position].name
        holder.description.text = characters[position].description
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.characterImageView)
        val name: TextView = itemView.findViewById(R.id.nameTextView)
        val description: TextView = itemView.findViewById(R.id.descriptionTextView)
    }
}

private class DownLoadImageTask(val imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {
    override fun doInBackground(vararg urls: String): Bitmap? {
        return try {
            val inputStream = URL(urls[0]).openStream()
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun onPostExecute(result: Bitmap?) {
        if (result != null) {
            imageView.setImageBitmap(result)
        }
    }
}