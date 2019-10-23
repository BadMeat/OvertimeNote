package com.dolan.arif.overtimenote.util

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

/**
 * Created by Bencoleng on 23/10/2019.
 */
class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private var mListener: DatePickerListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.let {
            mListener = context as DatePickerListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener?.let {
            mListener = null
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        context?.let {
            return DatePickerDialog(it, this, year, month, day)
        }
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        mListener?.onDialogSet(tag, year, month, day)
    }

    interface DatePickerListener {
        fun onDialogSet(tag: String?, year: Int, month: Int, day: Int)
    }
}