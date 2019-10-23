package com.dolan.arif.overtimenote.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dolan.arif.overtimenote.R
import com.dolan.arif.overtimenote.databinding.ItemMenuBinding
import com.dolan.arif.overtimenote.model.Menu

/**
 * Created by Bencoleng on 22/10/2019.
 */
class MenuAdapter : RecyclerView.Adapter<MenuAdapter.MenuHolder>() {

    private val menuList = mutableListOf<Menu>()

    fun setMenuList(e : List<Menu>){
        menuList.clear()
        menuList.addAll(e)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        val layout = LayoutInflater.from(parent.context)
        return MenuHolder(DataBindingUtil.inflate(layout, R.layout.item_menu, parent, false))
    }

    override fun getItemCount() = menuList.size

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        holder.view.menu = menuList[position]
    }

    class MenuHolder(val view: ItemMenuBinding) : RecyclerView.ViewHolder(view.root)

}