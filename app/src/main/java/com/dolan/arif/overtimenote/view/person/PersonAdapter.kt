package com.dolan.arif.overtimenote.view.person

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dolan.arif.overtimenote.R
import com.dolan.arif.overtimenote.databinding.ItemPersonBinding
import com.dolan.arif.overtimenote.model.Person

class PersonAdapter(private val listener: (Person) -> Unit) :
    RecyclerView.Adapter<PersonAdapter.PersonHolder>(), Filterable {

    private val personList = mutableListOf<Person>()
    private var personFilter = mutableListOf<Person>()

    fun setPerson(person: List<Person>) {
        personList.clear()
        personFilter.clear()
        personList.addAll(person)
        personFilter.addAll(person)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val input = constraint.toString()
                personFilter = if (input.isEmpty()) {
                    personList
                } else {
                    val result = mutableListOf<Person>()
                    for (row: Person in personList) {
                        if (row.name.contains(input, true)) {
                            result.add(row)
                        }
                    }
                    result
                }
                val filter = FilterResults()
                filter.values = personFilter
                return filter
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                results?.let {
                    @Suppress("UNCHECKED_CAST")
                    personFilter = it.values as MutableList<Person>
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
        val layout = LayoutInflater.from(parent.context)
        return PersonHolder(
            DataBindingUtil.inflate(layout, R.layout.item_person, parent, false),
            listener
        )
    }

    override fun getItemCount() = personFilter.size

    override fun onBindViewHolder(holder: PersonHolder, position: Int) {
        holder.view.person = personFilter[position]
        holder.bindItem()
    }


    class PersonHolder(val view: ItemPersonBinding, val listener: (Person) -> Unit) :
        RecyclerView.ViewHolder(view.root) {
        fun bindItem() {
            itemView.setOnClickListener {
                view.person?.let {
                    listener(it)
                }
            }
        }
    }
}