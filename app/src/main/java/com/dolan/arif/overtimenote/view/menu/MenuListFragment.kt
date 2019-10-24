package com.dolan.arif.overtimenote.view.menu


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
import com.dolan.arif.overtimenote.model.Menu
import com.dolan.arif.overtimenote.viewmodel.MenuListViewModel
import kotlinx.android.synthetic.main.fragment_menu_list.*

class MenuListFragment : Fragment() {

    private lateinit var menuAdapter: MenuListAdapter
    private lateinit var menuListViewModel: MenuListViewModel
    private var argDate = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuAdapter = MenuListAdapter {
            val action = MenuListFragmentDirections.actionMenuAdd(it)
            action.date = argDate
            action.type = "update"
            action.id = it.id
            action.menu = it
            Navigation.findNavController(view).navigate(action)
        }

        arguments?.let {
            argDate = MenuListFragmentArgs.fromBundle(it).date
            txt_date.text = argDate
        }

        rv_menu_list.apply {
            adapter = menuAdapter
            layoutManager = LinearLayoutManager(context)
        }

        menuListViewModel = ViewModelProviders.of(this).get(MenuListViewModel::class.java)
        menuListViewModel.findByDate(argDate)
        showData()

        btn_add.setOnClickListener {
            val action = MenuListFragmentDirections.actionMenuAdd(Menu("", "", ""))
            action.date = argDate
            action.type = "add"
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun showData() {
        menuListViewModel.menuList.observe(this, Observer { menu ->
            menu?.let {
                menuAdapter.setMenu(it)
            }
        })
        menuListViewModel.isLoading.observe(this, Observer { isLoading ->
            isLoading?.let {
                progress_bar.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }
}
