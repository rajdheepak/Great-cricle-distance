package com.dublin.greatcircledistance.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dublin.greatcircledistance.R
import com.dublin.greatcircledistance.model.LCE
import com.dublin.greatcircledistance.viewmodel.InviteCustomerViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: InviteCustomerViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        viewModel.customersWithinDistance.observe(this, {
            when (it) {
                LCE.Loading -> {
                    //updating loading state in UI
                }
                is LCE.Content -> {
                    findViewById<TextView>(R.id.expectedCustomers).text = it.customers.toString()
                }
                is LCE.Error -> {
                    //show appropriate error
                }
            }
        })
    }
}