package com.dolan.arif.overtimenote.view.person

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dolan.arif.overtimenote.R
import com.dolan.arif.overtimenote.databinding.ItemPersonBinding
import com.dolan.arif.overtimenote.model.Person

class PersonAdapter(private val listener: (Person) -> Unit) :
    RecyclerView.Adapter<PersonAdapter.PersonHolder>() {

    private val personList = mutableListOf<Person>()

    fun setPerson(person: List<Person>) {
        personList.clear()
        personList.addAll(person)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
        val layout = LayoutInflater.from(parent.context)
        return PersonHolder(
            DataBindingUtil.inflate(layout, R.layout.item_person, parent, false),
            listener
        )
    }

    override fun getItemCount() = personList.size

    override fun onBindViewHolder(holder: PersonHolder, position: Int) {
        holder.view.person = personList[position]
        holder.bindItem()
    }


    class PersonHolder(val view: ItemPersonBinding, val listener: (Person) -> Unit) :
        RecyclerView.ViewHolder(view.root) {
        fun bindItem() {
            itemView.setOnClickListener {
                listener(view.person!!)
            }
        }
    }
}