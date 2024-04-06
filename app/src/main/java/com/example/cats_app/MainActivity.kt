package com.example.cats_app
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cats_app.RetrofitInstance.API_KEY
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CatBreedAdapter
    private val catsApiService = RetrofitInstance.api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.catBreedsRecyclerView)
        adapter = CatBreedAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            try {
                val response = catsApiService.listCatBreeds(API_KEY).await()
                if (response.isSuccessful) {
                    val breeds = response.body()
                    if (!breeds.isNullOrEmpty()) {
                        adapter.updateData(breeds)
                    } else {
                        Toast.makeText(this@MainActivity, "Список пород кошек пуст.", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Ошибка: ${response.errorBody()?.string()}", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Ошибка соединения: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}