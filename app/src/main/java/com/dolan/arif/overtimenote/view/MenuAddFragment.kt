package com.dolan.arif.overtimenote.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dolan.arif.overtimenote.R
import com.dolan.arif.overtimenote.util.DatePickerFragment
import kotlinx.android.synthetic.main.fragment_menu_add.*
import java.text.SimpleDateFormat
import java.util.*

class MenuAddFragment : Fragment(), DatePickerFragment.DatePickerListener {

    override fun onDialogSet(tag: String?, year: Int, month: Int, day: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        txt_date.text = dateFormat.format(calendar.time)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_date.setOnClickListener {
            val datePicker = DatePickerFragment()
            fragmentManager?.let {
                datePicker.show(it, "DATE_PICKER")
            }
        }
    }
}
