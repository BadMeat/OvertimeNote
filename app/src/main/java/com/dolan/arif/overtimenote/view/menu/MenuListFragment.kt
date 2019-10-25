package com.dolan.arif.overtimenote.view.menu


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.dolan.arif.overtimenote.R
import com.dolan.arif.overtimenote.hideKeyboard
import com.dolan.arif.overtimenote.model.Menu
import com.dolan.arif.overtimenote.model.Report
import com.dolan.arif.overtimenote.reset
import com.dolan.arif.overtimenote.viewmodel.MenuListViewModel
import kotlinx.android.synthetic.main.fragment_menu_list.*

class MenuListFragment : Fragment(), View.OnClickListener, SearchView.OnQueryTextListener {

    private lateinit var menuAdapter: MenuListAdapter
    private lateinit var menuListViewModel: MenuListViewModel
    private var argDate = ""
    private lateinit var searchView: SearchView
    private var reportId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_menu_list, container, false)
    }

    override fun onPrepareOptionsMenu(menu: android.view.Menu) {
        val myMenu = menu.findItem(R.id.menu_search)
        searchView = myMenu.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuListViewModel = ViewModelProviders.of(this).get(MenuListViewModel::class.java)

        arguments?.let {
            argDate = MenuListFragmentArgs.fromBundle(it).date
            txt_date.text = argDate
            menuListViewModel.findReportByDate(argDate)
        }

        menuAdapter = MenuListAdapter {
            val action = MenuListFragmentDirections.actionMenuAdd(it)
            action.date = argDate
            action.type = "updateReport"
            action.menu = it
            Navigation.findNavController(view).navigate(action)
            searchView.reset()
        }

        rv_menu_list.apply {
            adapter = menuAdapter
            layoutManager = LinearLayoutManager(context)
        }


        menuListViewModel.findByDate(argDate)
        showData()

        btn_add.setOnClickListener(this)
        btn_save.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_add -> {
                val action = MenuListFragmentDirections.actionMenuAdd(Menu("", "", ""))
                action.date = argDate
                action.type = "add"
                Navigation.findNavController(v).navigate(action)
            }
            R.id.btn_save -> {
                val report = Report(argDate, input_total.text.toString().toInt())
                if (reportId != -1) {
                    report.id = reportId
                }
                menuListViewModel.saveReport(report)
                menuListViewModel.findReportByDate(argDate)
                hideKeyboard()
            }
        }
    }

    private fun showData() {
        menuListViewModel.menuList.observe(this, Observer { menu ->
            menu?.let {
                menuAdapter.setMenu(it)
            }
        })
        menuListViewModel.isLoading.observe(this, Observer { isLoading ->
            isLoading?.let {
                progress_bar.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
        menuListViewModel.report.observe(this, Observer { report ->
            report?.let {
                input_total.setText(it.total.toString())
                reportId = it.id
            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        menuAdapter.filter.filter(newText)
        return true
    }
}
