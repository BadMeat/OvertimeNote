package com.dolan.arif.overtimenote.view.person


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dolan.arif.overtimenote.R
import com.dolan.arif.overtimenote.model.Person
import com.dolan.arif.overtimenote.viewmodel.PersonViewModel
import kotlinx.android.synthetic.main.fragment_person_add.*

class PersonAddFragment : Fragment(), View.OnClickListener {

    private lateinit var personViewModel: PersonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_person_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_save.setOnClickListener(this)

        personViewModel = ViewModelProviders.of(this).get(PersonViewModel::class.java)
        showData()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_save -> {
                val person = Person(input_name.text.toString())
                personViewModel.save(person)
            }
        }
    }

    private fun showData() {
        personViewModel.isLoading.observe(this, Observer { isLoading ->
            isLoading?.let {
                progress_bar.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }
}
