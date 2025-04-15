package com.example.recipeapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.recipeapp.databinding.ActivitySearchBinding
import java.util.Locale
import kotlin.text.contains

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var rvAdapter: SearchAdaptor
    private lateinit var dataList: ArrayList<Recipe>
    private lateinit var recipes: List<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.goBackHome.setOnClickListener {
            finish()
        }
        binding.Search.requestFocus()

        var db = Room.databaseBuilder(this@SearchActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var DaoObject = db.dao()

        recipes = DaoObject?.getAll() as List<Recipe>

        setUpRecyclerView()

        binding.Search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (s.toString() != "") {
                    filterData(s.toString())
                }
            }

            private fun filterData(filterText: String) {
                var filterData = ArrayList<Recipe>()
                for (i in recipes.indices) {
                    if (recipes[i].tittle.lowercase().contains(filterText.lowercase())) {
                        filterData.add(recipes[i])
                    }
                }
                rvAdapter.filterList(filterData)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun setUpRecyclerView() {
        dataList = ArrayList()
        binding.rvSearch.layoutManager = LinearLayoutManager(this)
        for (i in recipes.indices) {
            if (recipes[i].category.contains("Popular")) {
                dataList.add(recipes[i])
            }
        }
        rvAdapter = SearchAdaptor(dataList, this)
        binding.rvSearch.adapter = rvAdapter
    }
}
