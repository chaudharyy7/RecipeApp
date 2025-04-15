package com.example.recipeapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.databinding.PopularItemsBinding

class PopularAdaptor(var dataList: ArrayList<Recipe>,var context: Context): RecyclerView.Adapter<PopularAdaptor.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        var binding = PopularItemsBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        Glide.with(context).load(dataList[position].img).into(holder.binding.popularImage)
        holder.binding.popularText.text = dataList[position].tittle
        var time = dataList[position].ing.split("\n".toRegex()).dropLastWhile { it.isEmpty()}.toTypedArray()
        holder.binding.popularTime.text = time[0]
        holder.itemView.setOnClickListener {
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

    inner class ViewHolder(var binding: PopularItemsBinding): RecyclerView.ViewHolder(binding.root)
}