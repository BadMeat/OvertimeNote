package com.dolan.arif.overtimenote.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.dolan.arif.overtimenote.R
import com.dolan.arif.overtimenote.viewmodel.MenuViewModel
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment(), View.OnClickListener {

    private val menuAdapter = MenuAdapter()
    private lateinit var menuViewModel: MenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_menu_add.setOnClickListener(this)
        btn_person_add.setOnClickListener(this)
        btn_food_add.setOnClickListener(this)
        rv_menu.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = menuAdapter
        }

        menuViewModel = ViewModelProviders.of(this).get(MenuViewModel::class.java)
        menuViewModel.getData()
        showData()
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_menu_add -> {
                val action = MenuFragmentDirections.actionMenuAdd()
                Navigation.findNavController(v).navigate(action)
            }
            R.id.btn_person_add -> {
                val action = MenuFragmentDirections.actionPersonAdd()
                Navigation.findNavController(v).navigate(action)
            }
            R.id.btn_food_add -> {
                val action = MenuFragmentDirections.actionFoodAdd()
                Navigation.findNavController(v).navigate(action)
            }
        }
    }

    private fun showData() {
        menuViewModel.menuList.observe(this, Observer { menu ->
            menu?.let {
                menuAdapter.setMenuList(it)
            }
        })
        menuViewModel.isLoading.observe(this, Observer { isLoading ->
            isLoading?.let {
                progress_bar.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }
}
