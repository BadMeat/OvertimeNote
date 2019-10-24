package com.dolan.arif.overtimenote.view.food


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dolan.arif.overtimenote.R
import com.dolan.arif.overtimenote.hideKeyboard
import com.dolan.arif.overtimenote.model.Food
import com.dolan.arif.overtimenote.showKeyboard
import com.dolan.arif.overtimenote.viewmodel.FoodViewModel
import kotlinx.android.synthetic.main.fragment_food_add.*

class FoodAddFragment : Fragment(), View.OnClickListener {

    private lateinit var foodViewModel: FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_save.setOnClickListener(this)
        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel::class.java)
        showData()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_save -> {
                val food = Food(input_name.text.toString())
                foodViewModel.save(food)
                input_name.text?.clear()
                hideKeyboard()
            }
        }
    }

    private fun showData() {
        foodViewModel.isLoading.observe(this, Observer { food ->
            food?.let {
                progress_bar.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }
}
