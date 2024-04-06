package com.example.cats_app

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class CatBreedAdapter(private var catBreeds: List<CatBreed>) :
    RecyclerView.Adapter<CatBreedAdapter.CatBreedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatBreedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cat_breed_item, parent, false)
        return CatBreedViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatBreedViewHolder, position: Int) {
        val catBreed = catBreeds[position]
        Log.d("CatBreedAdapter", "Binding view holder for ${catBreed.name}")
        holder.bind(catBreed)
    }

    override fun getItemCount() = catBreeds.size

    class CatBreedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        // Дополнительные переменные для других элементов UI...

    fun bind(catBreed: CatBreed) {
        nameTextView.text = catBreed.name
        // Привязка дополнительных полей catBreed к элементам UI...
    }
}
    fun updateData(newCatBreeds: List<CatBreed>) {
        val diffCallback = CatBreedDiffCallback(this.catBreeds, newCatBreeds)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        Log.d("CatBreedAdapter", "Updating data")
        this.catBreeds = newCatBreeds
        diffResult.dispatchUpdatesTo(this)
    }
    class CatBreedDiffCallback(
        private val oldList: List<CatBreed>,
        private val newList: List<CatBreed>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val same = oldList[oldItemPosition].name == newList[newItemPosition].name
            Log.d("DiffCallback", "areItemsTheSame: $same")
            return same
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val same = oldList[oldItemPosition] == newList[newItemPosition]
            Log.d("DiffCallback", "areContentsTheSame: $same")
            return same
        }
    }
}
