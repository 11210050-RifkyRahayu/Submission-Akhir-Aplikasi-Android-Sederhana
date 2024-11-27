package com.example.animelist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvAnime: RecyclerView
    private val list = ArrayList<Anime>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAnime = findViewById(R.id.rv_anime)
        rvAnime.setHasFixedSize(true)
        list.addAll(getListAnime())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {

                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getListAnime(): ArrayList<Anime> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listAnime = ArrayList<Anime>()
        for (i in dataName.indices) {
            val anime = Anime(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listAnime.add(anime)
        }
        return listAnime
    }

    private fun showRecyclerList() {
        rvAnime.layoutManager = LinearLayoutManager(this)
        val listAnimeAdapter = ListAnimeAdapter(list)
        rvAnime.adapter = listAnimeAdapter

        listAnimeAdapter.setOnItemClickCallback(object : ListAnimeAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Anime) {
                showSelectedAnime(data)
            }
        })
    }
    private fun showSelectedAnime(anime: Anime) {
        val intent = Intent(this, DetailAnimeActivity::class.java)
        intent.putExtra(DetailAnimeActivity.EXTRA_NAME, anime.name)
        intent.putExtra(DetailAnimeActivity.EXTRA_DESCRIPTION, anime.description)
        intent.putExtra(DetailAnimeActivity.EXTRA_PHOTO, anime.photo)
        startActivity(intent)
    }

}