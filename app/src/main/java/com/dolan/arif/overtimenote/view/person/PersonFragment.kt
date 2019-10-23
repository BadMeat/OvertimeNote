package com.dolan.arif.overtimenote.view.person


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.dolan.arif.overtimenote.R
import com.dolan.arif.overtimenote.viewmodel.MenuAddViewModel
import kotlinx.android.synthetic.main.fragment_person.*

class PersonFragment : Fragment() {

    private lateinit var menuAddViewModel: MenuAddViewModel
    private lateinit var personAdapter: PersonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_person, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuAddViewModel = ViewModelProviders.of(activity!!).get(MenuAddViewModel::class.java)
        menuAddViewModel.getDataPerson()
        menuAddViewModel.viewModelScope

        personAdapter = PersonAdapter {
            menuAddViewModel.setPerson(it)
            Navigation.findNavController(view).popBackStack()
        }

        rv_person.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = personAdapter
        }

        showData()
    }

    private fun showData() {
        menuAddViewModel.personList.observe(this, Observer { person ->
            person?.let {
                personAdapter.setPerson(it)
            }
        })
    }
}
