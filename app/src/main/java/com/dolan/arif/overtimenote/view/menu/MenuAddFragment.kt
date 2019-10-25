package com.dolan.arif.overtimenote.view.menu


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.dolan.arif.overtimenote.R
import com.dolan.arif.overtimenote.model.Menu
import com.dolan.arif.overtimenote.viewmodel.MenuAddViewModel
import kotlinx.android.synthetic.main.fragment_menu_add.*

class MenuAddFragment : Fragment(), View.OnClickListener {

    private lateinit var menuAddViewModel: MenuAddViewModel
    private var typeArg = "add"
    private lateinit var menu: Menu

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_menu_add, container, false)
    }

    override fun onPrepareOptionsMenu(menu: android.view.Menu) {
        val myMenu = menu.findItem(R.id.menu_search)
        myMenu.isVisible = false
        super.onPrepareOptionsMenu(menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuAddViewModel = ViewModelProviders.of(activity!!).get(MenuAddViewModel::class.java)

        btn_save.setOnClickListener(this)
        btn_person.setOnClickListener(this)
        btn_food.setOnClickListener(this)
        btn_delete.setOnClickListener(this)

        arguments?.let {
            val arg = MenuAddFragmentArgs.fromBundle(it)
            val date = arg.date
            typeArg = arg.type
            txt_date.text = date
            if (typeArg.equals("updateReport", true)) {
                menu = arg.menu
                txt_name.setText(menu.person)
                txt_food.setText(menu.food)
            } else {
                btn_delete.visibility = View.GONE
            }
        }

        showData()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_save -> {
                val name = txt_name.text.toString()
                val food = txt_food.text.toString()
                val tempMenu = Menu(name, food, txt_date.text.toString())
                if (typeArg.equals("updateReport", true)) {
                    tempMenu.id = menu.id
                }
                menuAddViewModel.saveMenu(tempMenu, typeArg)
                Navigation.findNavController(v).popBackStack()
            }

            R.id.btn_delete -> {
                menuAddViewModel.deleteMenu(menu)
                Navigation.findNavController(v).popBackStack()
            }

            R.id.btn_person -> {
                val action = MenuAddFragmentDirections.actionPerson()
                Navigation.findNavController(v).navigate(action)
            }
            R.id.btn_food -> {
                val action = MenuAddFragmentDirections.actionFood()
                Navigation.findNavController(v).navigate(action)
            }
        }
    }

    private fun showData() {
        menuAddViewModel.menu.observe(this, Observer { menu ->
            menu?.let {
                txt_name.setText(it.person)
                txt_food.setText(it.food)
            }
        })
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
