package com.dolan.arif.overtimenote.view.food


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
import com.dolan.arif.overtimenote.viewmodel.MenuAddViewModel
import kotlinx.android.synthetic.main.fragment_food.*

class FoodFragment : Fragment() {

    private lateinit var foodAdapter: FoodAdapter
    private lateinit var menuAddViewModel: MenuAddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        foodAdapter = FoodAdapter {
            menuAddViewModel.setFood(it)
            Navigation.findNavController(view).popBackStack()
        }

        rv_food.apply {
            layoutManager = LinearLayoutManager(context)
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
    }
}
