package com.dolan.arif.overtimenote.view.menu


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
    private var typeArg = "add"
    private var menuId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuAddViewModel = ViewModelProviders.of(activity!!).get(MenuAddViewModel::class.java)

        arguments?.let {
            val arg = MenuAddFragmentArgs.fromBundle(it)
            val date = arg.date
            typeArg = arg.type
            txt_date.text = date
            if (typeArg.equals("update", true)) {
                menuId = arg.id
                val menu = arg.menu
                txt_name.setText(menu.person)
                txt_food.setText(menu.food)
//                menuAddViewModel.findById(menuId)
            }
        }

        btn_save.setOnClickListener {
            val name = txt_name.text.toString()
            val food = txt_food.text.toString()
            val menu = Menu(name, food, txt_date.text.toString())
            if (typeArg.equals("update", true)) {
                Log.d("IDPASSING", "$menuId")
                menu.id = menuId
            }
            menuAddViewModel.saveMenu(menu, typeArg)
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
                Log.d("MASUKSINI", "MASUK SINI DONG")
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
