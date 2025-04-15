package com.example.recipeapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.databinding.SearchRvBinding

class SearchAdaptor(var dataList:ArrayList<Recipe>,var context: Context): RecyclerView.Adapter<SearchAdaptor.viewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): viewHolder {
        var view = SearchRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return viewHolder(view)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filteredList: ArrayList<Recipe>) {
        dataList = filteredList
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(
        holder: viewHolder,
        position: Int
    ) {
        Glide.with(context).load(dataList[position].img).into(holder.binding.Searchimg)
        holder.binding.searchTxt.text = dataList[position].tittle
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

    inner class viewHolder(var binding: SearchRvBinding): RecyclerView.ViewHolder(binding.root)
}