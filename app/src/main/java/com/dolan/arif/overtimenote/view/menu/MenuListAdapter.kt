package com.dolan.arif.overtimenote.view.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dolan.arif.overtimenote.R
import com.dolan.arif.overtimenote.databinding.ItemMenuListBinding
import com.dolan.arif.overtimenote.model.Menu

/**
 * Created by Bencoleng on 23/10/2019.
 */
class MenuListAdapter(private val listener: (Menu) -> Unit) :
    RecyclerView.Adapter<MenuListAdapter.MenuListHolder>(), Filterable {


    private val menuList = mutableListOf<Menu>()
    private var menuFilter = mutableListOf<Menu>()

    fun setMenu(e: List<Menu>) {
        menuList.clear()
        menuFilter.clear()
        menuList.addAll(e)
        menuFilter.addAll(e)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val input = constraint.toString()
                val resultList = if (input.isEmpty()) {
                    menuList
                } else {
                    val tempResult = mutableListOf<Menu>()
                    for (e: Menu in menuList) {
                        e.person?.let {
                            if (it.contains(input, true)) {
                                tempResult.add(e)
                            }
                        }
                    }
                    tempResult
                }
                val result = FilterResults()
                result.values = resultList
                return result

            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                results?.let {
                    @Suppress("UNCHECKED_CAST")
                    menuFilter = it.values as MutableList<Menu>
                    notifyDataSetChanged()
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuListHolder {
        val layout = LayoutInflater.from(parent.context)
        return MenuListHolder(
            DataBindingUtil.inflate(
                layout,
                R.layout.item_menu_list,
                parent,
                false
            )
            , listener
        )
    }

    override fun getItemCount() = menuFilter.size

    override fun onBindViewHolder(holder: MenuListHolder, position: Int) {
        holder.view.menu = menuFilter[position]
        holder.onClick()
    }

    class MenuListHolder(val view: ItemMenuListBinding, val listener: (Menu) -> Unit) :
        RecyclerView.ViewHolder(view.root) {
        fun onClick() {
            itemView.setOnClickListener {
                listener(view.menu!!)
            }
        }
    }
}