package com.example.recipeapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.recipeapp.HomeActivity
import com.example.recipeapp.databinding.ActivityCategoryBinding
import kotlin.text.contains

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    private lateinit var rvAdapter: CategoryAdaptor
    private lateinit var dataList: ArrayList<Recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.BackBtn.setOnClickListener {
            finish()
        }
        binding.tittle.text = intent.getStringExtra("TITTLE")
        setUpRecyclerView()
    }
    private fun setUpRecyclerView() {
        dataList = ArrayList()
        binding.CategoryRecyclerView.layoutManager = LinearLayoutManager(this)
        var db = Room.databaseBuilder(this@CategoryActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var DaoObject = db.dao()
        var recipes = DaoObject?.getAll()
        for (i in recipes!!.indices) {
            if (recipes[i]!!.category.contains(intent.getStringExtra("CATEGORY").toString())) {
                dataList.add(recipes[i]!!)
            }
        }
        rvAdapter = CategoryAdaptor(dataList, this)
        binding.CategoryRecyclerView.adapter = rvAdapter
    }
}