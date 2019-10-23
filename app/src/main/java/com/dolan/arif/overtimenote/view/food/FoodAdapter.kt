package com.dolan.arif.overtimenote.view.food

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dolan.arif.overtimenote.R
import com.dolan.arif.overtimenote.databinding.ItemFoodBinding
import com.dolan.arif.overtimenote.model.Food

class FoodAdapter(private val listener: (Food) -> Unit) : RecyclerView.Adapter<FoodAdapter.FoodHolder>() {

    private val foodList = mutableListOf<Food>()

    fun setFood(food: List<Food>) {
        foodList.clear()
        foodList.addAll(food)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val layout = LayoutInflater.from(parent.context)
        return FoodHolder(
            DataBindingUtil.inflate(layout, R.layout.item_food, parent, false),
            listener
        )
    }

    override fun getItemCount() = foodList.size

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.view.food = foodList[position]
        holder.onClick()
    }

    class FoodHolder(val view: ItemFoodBinding, val listener: (Food) -> Unit) :
        RecyclerView.ViewHolder(view.root) {
        fun onClick() {
            itemView.setOnClickListener {
                listener(view.food!!)
            }
        }
    }
}