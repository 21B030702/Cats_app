package com.example.cats_app
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cats_app.RetrofitInstance.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CatBreedAdapter
    private val catsApiService = RetrofitInstance.api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.catBreedsRecyclerView)
        adapter = CatBreedAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        fetchBreeds(10)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.toIntOrNull()?.let { minLifeExpectancy ->
                    fetchBreeds(minLifeExpectancy)
                } ?: Toast.makeText(this@MainActivity, "Введите числовое значение продолжительности жизни", Toast.LENGTH_LONG).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        return true
    }
    private fun fetchBreeds(minLifeExpectancy: Int?) {
        lifecycleScope.launch {
            try {
                val response = catsApiService.listCatBreeds(API_KEY, minLifeExpectancy)
                if (response.isSuccessful) {
                    response.body()?.let { breeds ->
                        withContext(Dispatchers.Main) {
                            adapter.updateData(breeds)
                        }
                    }
                } else {
                    handleError(response.errorBody()?.string())
                }
            } catch (e: Exception) {
                handleError(e.message)
            }
        }
    }

    private fun handleError(message: String?) {
        message?.let {
            Toast.makeText(this@MainActivity, "Ошибка: $it", Toast.LENGTH_LONG).show()
        }
    }
}