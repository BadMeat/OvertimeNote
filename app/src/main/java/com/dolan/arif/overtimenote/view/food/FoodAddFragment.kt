package com.dolan.arif.overtimenote.view.food


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.dolan.arif.overtimenote.R
import com.dolan.arif.overtimenote.model.Food
import com.dolan.arif.overtimenote.viewmodel.FoodViewModel
import kotlinx.android.synthetic.main.fragment_food_add.*

class FoodAddFragment : Fragment() {

    private lateinit var foodViewModel: FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel::class.java)

        btn_save.setOnClickListener {
            val food = Food(input_name.text.toString())
            foodViewModel.save(food)
        }
    }
}
