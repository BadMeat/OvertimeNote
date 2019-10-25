package com.dolan.arif.overtimenote.view.person


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.dolan.arif.overtimenote.R
import com.dolan.arif.overtimenote.hideKeyboard
import com.dolan.arif.overtimenote.model.Person
import com.dolan.arif.overtimenote.showKeyboard
import com.dolan.arif.overtimenote.viewmodel.PersonViewModel
import kotlinx.android.synthetic.main.fragment_food_add.*
import kotlinx.android.synthetic.main.fragment_person_add.*
import kotlinx.android.synthetic.main.fragment_person_add.btn_save
import kotlinx.android.synthetic.main.fragment_person_add.input_name
import kotlinx.android.synthetic.main.fragment_person_add.progress_bar

class PersonAddFragment : Fragment(), View.OnClickListener {

    private lateinit var personViewModel: PersonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_person_add, container, false)
    }

    override fun onPrepareOptionsMenu(menu: android.view.Menu) {
        val myMenu = menu.findItem(R.id.menu_search)
        myMenu.isVisible = false
        super.onPrepareOptionsMenu(menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_save.setOnClickListener(this)

        personViewModel = ViewModelProviders.of(this).get(PersonViewModel::class.java)
        showData()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_save -> {
                val person = Person(input_name.text.toString())
                personViewModel.save(person)
                input_name.text?.clear()
                hideKeyboard()
                Navigation.findNavController(v).popBackStack()
            }
        }
    }

    private fun showData() {
        input_name.requestFocus()
        input_name.isFocusableInTouchMode = true
        showKeyboard()
        personViewModel.isLoading.observe(this, Observer { isLoading ->
            isLoading?.let {
                progress_bar.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }
}
