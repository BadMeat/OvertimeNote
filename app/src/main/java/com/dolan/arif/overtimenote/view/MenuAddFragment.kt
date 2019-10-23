package com.dolan.arif.overtimenote.view


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.dolan.arif.overtimenote.R
import com.dolan.arif.overtimenote.model.Menu
import com.dolan.arif.overtimenote.viewmodel.MenuAddViewModel
import kotlinx.android.synthetic.main.fragment_menu_add.*

class MenuAddFragment : Fragment() {

    private lateinit var menuAddViewModel: MenuAddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuAddViewModel = ViewModelProviders.of(activity!!).get(MenuAddViewModel::class.java)

        btn_save.setOnClickListener {
            val name = txt_name.text.toString()
            val food = txt_food.text.toString()
            val menu = Menu(name, food, "12-12-2019")
            menuAddViewModel.saveMenu(menu)
        }

        btn_person.setOnClickListener {
            val action = MenuAddFragmentDirections.actionPerson()
            Navigation.findNavController(it).navigate(action)
        }

        btn_food.setOnClickListener {
            val action = MenuAddFragmentDirections.actionFood()
            Navigation.findNavController(it).navigate(action)
        }

        showData()
    }

    private fun showData() {
        menuAddViewModel.isLoading.observe(this, Observer { isLoading ->
            isLoading?.let {
                progress_bar.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
        menuAddViewModel.person.observe(this, Observer { person ->
            person?.let {
                txt_name.setText(it.name)
            }
        })
        menuAddViewModel.food.observe(this, Observer { food ->
            food?.let {
                txt_food.setText(it.name)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.viewModelStore?.clear()
    }
}
