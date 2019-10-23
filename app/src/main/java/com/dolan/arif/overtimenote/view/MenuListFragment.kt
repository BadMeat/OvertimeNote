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
import androidx.recyclerview.widget.LinearLayoutManager
import com.dolan.arif.overtimenote.R
import com.dolan.arif.overtimenote.viewmodel.MenuListViewModel
import kotlinx.android.synthetic.main.fragment_menu_list.*

class MenuListFragment : Fragment() {

    private val menuAdapter = MenuAdapter()
    private lateinit var menuListViewModel: MenuListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val date = MenuListFragmentArgs.fromBundle(it).date
            Log.d("DATENYAMANG", "$date")
        }

        rv_menu_list.apply {
            adapter = menuAdapter
            layoutManager = LinearLayoutManager(context)
        }

        menuListViewModel = ViewModelProviders.of(this).get(MenuListViewModel::class.java)
        menuListViewModel.getData()
        showData()

        btn_add.setOnClickListener {
            val action = MenuListFragmentDirections.actionMenuAdd()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun showData() {
        menuListViewModel.menuList.observe(this, Observer { menu ->
            menu?.let {
                menuAdapter.setMenuList(it)
            }
        })
        menuListViewModel.isLoading.observe(this, Observer { isLoading ->
            isLoading?.let {
                progress_bar.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }
}
