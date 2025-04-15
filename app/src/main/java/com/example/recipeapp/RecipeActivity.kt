package com.example.recipeapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.recipeapp.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding
    var imgCrop =  true
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImage)
        binding.tittle.text = intent.getStringExtra("tittle")
        binding.stepData.text=intent.getStringExtra("des")
        var ing = intent.getStringExtra("ing")?.split("\n".toRegex())?.dropLastWhile { it.isEmpty()}?.toTypedArray()
        binding.time.text=ing?.get(0)
        for (i in 1 until ing!!.size){
            binding.ingData.text = "${binding.ingData.text}🟢 ${ing[i]}\n"

        }
        binding.steps.background = null
        binding.steps.setOnClickListener {
            binding.steps.setBackgroundResource(R.drawable.btn_ing)
            binding.steps.setTextColor(Color.WHITE)
            binding.ing.setTextColor(Color.BLACK)
            binding.ing.background=null
            binding.stepsScroll.visibility = View.VISIBLE
            binding.ingScroll.visibility = View.GONE

        }
        binding.ing.setOnClickListener {
            binding.ing.setBackgroundResource(R.drawable.btn_ing)
            binding.ing.setTextColor(Color.WHITE)
            binding.steps.setTextColor(Color.BLACK)
            binding.steps.background=null
            binding.ingScroll.visibility = View.VISIBLE
            binding.stepsScroll.visibility = View.GONE
        }
        binding.fullScreen.setOnClickListener {
            if (imgCrop) {
                binding.itemImage.scaleType = ImageView.ScaleType.FIT_CENTER
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImage)
                binding.fullScreen.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)
                binding.shade.visibility = GONE
                imgCrop = false
            } else {
                binding.itemImage.scaleType = ImageView.ScaleType.CENTER_CROP
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImage)
                binding.fullScreen.setColorFilter(null)
                binding.shade.visibility = GONE
                imgCrop = true
            }
        }
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}