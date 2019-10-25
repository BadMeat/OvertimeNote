package com.dolan.arif.overtimenote.view.report


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dolan.arif.overtimenote.R
import com.dolan.arif.overtimenote.viewmodel.ReportViewModel
import kotlinx.android.synthetic.main.fragment_report.*

class ReportFragment : Fragment() {

    private lateinit var reportViewModel: ReportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    override fun onPrepareOptionsMenu(menu: android.view.Menu) {
        val myMenu = menu.findItem(R.id.menu_search)
        myMenu.isVisible = false
        super.onPrepareOptionsMenu(menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chart.barMaxValue = 1000000

        reportViewModel = ViewModelProviders.of(this).get(ReportViewModel::class.java)
        reportViewModel.findAll()
        showDate()
    }

    private fun showDate() {
        reportViewModel.bartChartList.observe(this, Observer { model ->
            model?.let {
                chart.addBar(it)
            }
        })
    }
}
