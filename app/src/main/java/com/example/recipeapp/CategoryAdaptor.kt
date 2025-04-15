package com.example.recipeapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.databinding.CategoryRvBinding

class CategoryAdaptor(var dataList: ArrayList<Recipe>, var context: Context): RecyclerView.Adapter<CategoryAdaptor.viewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): viewHolder {
        var view = CategoryRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(
        holder: viewHolder,
        position: Int
    ) {
        Glide.with(context).load(dataList[position].img).into(holder.binding.img)
        holder.binding.tittle.text = dataList[position].tittle
        var temp = dataList[position].ing.split("\n").dropLastWhile{it.isEmpty()}.toTypedArray()
        holder.binding.time.text = temp[0]
        holder.binding.next.setOnClickListener {
            val intent = Intent(context,RecipeActivity::class.java)
            intent.putExtra("img",dataList[position].img)
            intent.putExtra("tittle",dataList[position].tittle)
            intent.putExtra("des",dataList[position].des)
            intent.putExtra("ing",dataList[position].ing)
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class viewHolder(var binding: CategoryRvBinding) : RecyclerView.ViewHolder(binding.root)
}