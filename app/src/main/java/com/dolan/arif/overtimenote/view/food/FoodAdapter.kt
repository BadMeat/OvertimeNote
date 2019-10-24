package com.dolan.arif.overtimenote.view.food

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dolan.arif.overtimenote.R
import com.dolan.arif.overtimenote.databinding.ItemFoodBinding
import com.dolan.arif.overtimenote.model.Food

class FoodAdapter(private val listener: (Food) -> Unit) :
    RecyclerView.Adapter<FoodAdapter.FoodHolder>(), Filterable {

    private val foodList = mutableListOf<Food>()
    private var filterData = mutableListOf<Food>()

    fun setFood(food: List<Food>) {
        foodList.clear()
        filterData.clear()
        foodList.addAll(food)
        filterData.addAll(food)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val input = constraint.toString()
                filterData = if (input.isEmpty()) {
                    foodList
                } else {
                    val result = mutableListOf<Food>()
                    for (row: Food in foodList) {
                        if (row.name.contains(input, true)) {
                            result.add(row)
                        }

                    }
                    result
                }
                val filterResult = FilterResults()
                filterResult.values = filterData
                return filterResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                results?.let {
                    @Suppress("UNCHECKED_CAST")
                    filterData = it.values as MutableList<Food>
                    notifyDataSetChanged()
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val layout = LayoutInflater.from(parent.context)
        return FoodHolder(
            DataBindingUtil.inflate(layout, R.layout.item_food, parent, false),
            listener
        )
    }

    override fun getItemCount() = filterData.size

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.view.food = filterData[position]
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