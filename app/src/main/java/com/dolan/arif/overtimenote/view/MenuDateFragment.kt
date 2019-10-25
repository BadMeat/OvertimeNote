package com.dolan.arif.overtimenote.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.dolan.arif.overtimenote.R
import com.dolan.arif.overtimenote.util.DatePickerFragment
import kotlinx.android.synthetic.main.fragment_menu_date.*
import java.text.SimpleDateFormat
import java.util.*

class MenuDateFragment : Fragment(), DatePickerFragment.DatePickerListener {

    override fun onDialogSet(tag: String?, year: Int, month: Int, day: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        txt_date.setText(dateFormat.format(calendar.time))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_menu_date, container, false)
    }

    override fun onPrepareOptionsMenu(menu: android.view.Menu) {
        val myMenu = menu.findItem(R.id.menu_search)
        myMenu.isVisible = false
        super.onPrepareOptionsMenu(menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_date.setOnClickListener {
            val datePicker = DatePickerFragment()
            fragmentManager?.let {
                datePicker.show(it, "DATE_PICKER")
            }
        }
        btn_save.setOnClickListener {
            val action = MenuDateFragmentDirections.actionMenuList()
            action.date = txt_date.text.toString()
            Navigation.findNavController(it).navigate(
                action
            )
        }
    }
}
