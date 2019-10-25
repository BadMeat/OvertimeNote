package com.dolan.arif.overtimenote.view.menu


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dolan.arif.overtimenote.R
import com.dolan.arif.overtimenote.viewmodel.MenuCountViewModel
import kotlinx.android.synthetic.main.fragment_menu_count.*

class MenuCountFragment : Fragment() {

    private lateinit var menuCountViewModel: MenuCountViewModel
    private val menuCountAdapter = MenuCountAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_menu_count, container, false)
    }

    override fun onPrepareOptionsMenu(menu: android.view.Menu) {
        val myMenu = menu.findItem(R.id.menu_search)
        myMenu.isVisible = false
        super.onPrepareOptionsMenu(menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuCountViewModel = ViewModelProviders.of(this).get(MenuCountViewModel::class.java)
        rv_menu_count.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = menuCountAdapter
        }

        arguments?.let {
            val date = MenuCountFragmentArgs.fromBundle(it).date
            menuCountViewModel.getData(date)
        }

        setData()
    }

    private fun setData() {
        menuCountViewModel.countList.observe(this, Observer { count ->
            count?.let {
                menuCountAdapter.setCountList(it)
            }
        })
    }
}
