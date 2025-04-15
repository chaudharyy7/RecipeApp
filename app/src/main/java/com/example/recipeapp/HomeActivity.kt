package com.example.recipeapp

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.recipeapp.databinding.ActivityHomeBinding
import kotlin.jvm.java

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvAdapter: PopularAdaptor
    private lateinit var dataList: ArrayList<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpRecyclerView()
        binding.search.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

        binding.mainDish.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("CATEGORY", "Dish")
            intent.putExtra("TITTLE", "Main")
            startActivity(intent)
        }
        binding.salad.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("CATEGORY", "Salad")
            intent.putExtra("TITTLE", "Salad")
            startActivity(intent)
        }
        binding.Drinks.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("CATEGORY", "Drinks")
            intent.putExtra("TITTLE", "Drinks")
            startActivity(intent)
        }
        binding.Desert.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("CATEGORY", "Dessert")
            intent.putExtra("TITTLE", "Dessert")
            startActivity(intent)
        }
        binding.more.setOnClickListener {
            val Dialog = Dialog(this)
            Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            Dialog.setContentView(R.layout.bottom_sheets)
            Dialog.show()
            Dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            Dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
            Dialog.window!!.setGravity(Gravity.BOTTOM)
        }
    }
    private fun setUpRecyclerView() {
        dataList = ArrayList()
        binding.RecyclerViewPopular.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        var db = Room.databaseBuilder(this@HomeActivity,AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var DaoObject = db.dao()
        var recipes = DaoObject?.getAll()
        for (i in recipes!!.indices) {
            if (recipes[i]!!.category.contains("Popular")){
                dataList.add(recipes[i]!!)
            }
        }
        rvAdapter = PopularAdaptor(dataList, this@HomeActivity)
        binding.RecyclerViewPopular.adapter = rvAdapter
    }
}
