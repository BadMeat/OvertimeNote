package com.dolan.arif.overtimenote.view.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dolan.arif.overtimenote.R
import com.dolan.arif.overtimenote.databinding.ItemMenuListBinding
import com.dolan.arif.overtimenote.model.Menu

/**
 * Created by Bencoleng on 23/10/2019.
 */
class MenuListAdapter(private val listener: (Menu) -> Unit) :
    RecyclerView.Adapter<MenuListAdapter.MenuListHolder>() {

    private val menuList = mutableListOf<Menu>()

    fun setMenu(e: List<Menu>) {
        menuList.clear()
        menuList.addAll(e)
        notifyDataSetChanged()
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

    override fun getItemCount() = menuList.size

    override fun onBindViewHolder(holder: MenuListHolder, position: Int) {
        holder.view.menu = menuList[position]
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