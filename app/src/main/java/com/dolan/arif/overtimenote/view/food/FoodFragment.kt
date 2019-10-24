package com.dolan.arif.overtimenote.view.food


import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.dolan.arif.overtimenote.R
import com.dolan.arif.overtimenote.reset
import com.dolan.arif.overtimenote.viewmodel.MenuAddViewModel
import kotlinx.android.synthetic.main.fragment_food.*

class FoodFragment : Fragment(), SearchView.OnQueryTextListener {


    private lateinit var foodAdapter: FoodAdapter
    private lateinit var menuAddViewModel: MenuAddViewModel
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_food, container, false)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val myMenu = menu.findItem(R.id.menu_search)
        searchView = myMenu.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        foodAdapter = FoodAdapter {
            menuAddViewModel.setFood(it)
            searchView.reset()
            Navigation.findNavController(view).popBackStack()
        }

        rv_food.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = foodAdapter
        }

        menuAddViewModel = ViewModelProviders.of(activity!!).get(MenuAddViewModel::class.java)
        menuAddViewModel.getDataFood()
        showData()
    }

    private fun showData() {
        menuAddViewModel.foodList.observe(this, Observer { food ->
            food?.let {
                foodAdapter.setFood(it)
            }
        })
        menuAddViewModel.isLoading.observe(this, Observer { isLoading ->
            isLoading?.let {
                progress_bar.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        foodAdapter.filter.filter(newText)
        return true
    }

}
