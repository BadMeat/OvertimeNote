package com.dolan.arif.overtimenote.view.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dolan.arif.overtimenote.R
import com.dolan.arif.overtimenote.databinding.ItemMenuCountBinding
import com.dolan.arif.overtimenote.model.CountWrapper

class MenuCountAdapter : RecyclerView.Adapter<MenuCountAdapter.MenuCountHolder>() {

    private val countList = mutableListOf<CountWrapper>()

    fun setCountList(count: List<CountWrapper>) {
        countList.clear()
        countList.addAll(count)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuCountHolder {
        val layout = LayoutInflater.from(parent.context)
        return MenuCountHolder(
            DataBindingUtil.inflate(
                layout,
                R.layout.item_menu_count,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = countList.size

    override fun onBindViewHolder(holder: MenuCountHolder, position: Int) {
        holder.v.count = countList[position]
    }


    class MenuCountHolder(val v: ItemMenuCountBinding) : RecyclerView.ViewHolder(v.root)
}