package com.example.rickandmortyapp.favoriteCharacter

import android.annotation.SuppressLint
import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.FavoriteCharacterModel
import com.example.rickandmortyapp.databinding.ItemCharacterBinding
import java.text.SimpleDateFormat

class FavoriteCharacterAdapter(
    private val onItemClicked: (FavoriteCharacterModel) -> Unit
) : ListAdapter<FavoriteCharacterModel, FavoriteCharacterAdapter.FavCharViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<FavoriteCharacterModel>() {
            override fun areItemsTheSame(oldItem: FavoriteCharacterModel, newItem: FavoriteCharacterModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FavoriteCharacterModel, newItem: FavoriteCharacterModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavCharViewHolder {
        val viewHolder = FavCharViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from( parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: FavCharViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FavCharViewHolder(
        private var binding: ItemCharacterBinding
    ): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(favoriteCharacterModel: FavoriteCharacterModel) {
            binding.characterText.text = favoriteCharacterModel.name
        }
    }
}















/*

class FavoriteCharacterAdapter : RecyclerView.Adapter<FavoriteCharacterAdapter.MyViewHolder>() {

    private var userList = emptyList<FavoriteCharacterModel>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false))
        /*
                val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return MyViewHolder(binding)
        */

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.findViewById<Button>(R.id.character_text).text = currentItem.name
        /*
        holder.bind(currentItem)
         */
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(favoriteCharacter: List<FavoriteCharacterModel>) {
        this.userList = favoriteCharacter
        notifyDataSetChanged()
    }


}






















class FavoriteCharacterAdapter(private var items: List<ClipData.Item>) : RecyclerView.Adapter<FavoriteCharacterAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.character_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]
        holder.itemName.text = currentItem.name
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newItems: List<ClipData.Item>) {
        items = newItems
        notifyDataSetChanged()
    }
}










class FavoriteCharacterAdapter : RecyclerView.Adapter<FavoriteCharacterAdapter.MyViewHolder>() {

    private var userList = emptyList<FavoriteCharacterModel>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.character_text.text = currentItem.name
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(favoriteCharacter: List<FavoriteCharacterModel>) {
        this.userList = favoriteCharacter
        notifyDataSetChanged()
    }




 */



    /*






 class FavoriteCharacterAdapter : RecyclerView.Adapter<FavoriteCharacterAdapter.ViewHolder>() {

 private var items = emptyList<ClipData.Item>()

 inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
     val characterText: TextView = itemView.findViewById(R.id.character_text)
 }

 override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
     return ViewHolder(view)
 }

 override fun onBindViewHolder(holder: ViewHolder, position: Int) {
     val currentItem = items[position]
     holder.characterText.text = currentItem.name
 }

 override fun getItemCount(): Int {
     return items.size
 }

 @SuppressLint("NotifyDataSetChanged")
 fun setData(newItems: List<ClipData.Item>) {
     items = newItems
     notifyDataSetChanged()
 }


}

     */