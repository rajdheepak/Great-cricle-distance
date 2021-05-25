package com.dublin.greatcircledistance.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dublin.greatcircledistance.domain.CustomerListRepository
import com.dublin.greatcircledistance.model.Customer
import com.dublin.greatcircledistance.model.Data
import com.dublin.greatcircledistance.model.LCE
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlin.math.*

class InviteCustomerViewModel(private val customerListRepository: CustomerListRepository) :
    ViewModel() {

    val customersWithinDistance: LiveData<LCE> = customerListRepository.getCustomers().map { it ->
        when (it) {
            is Data.Success -> {
                val customersWithinRadius: MutableList<Customer> = mutableListOf()
                it.customers.forEach {
                    val distance = distanceFromDublinOffice(it.latitude,it.longitude)
                    if(distance <=  100) {
                        customersWithinRadius.add(it)
                    }
                }
                LCE.Content(customersWithinRadius.sortedBy { it.userId }.map { "UserID : ${it.userId} Name : ${it.name}" })
            }
            is Data.Error -> LCE.Error(it.errorMessage)
            Data.Empty -> LCE.Loading
        }
    }.onStart { emit(LCE.Loading) }
        .distinctUntilChanged()
        .asLiveData()

    private fun distanceFromDublinOffice(
        lat1: Double, lon1: Double,
    ): Double {
        var lat1 = Math.toRadians(lat1)
        var lat2 = Math.toRadians(53.339428)
        var lon1 = Math.toRadians(lon1)
        var lon2 = Math.toRadians(-6.257664)
        val centreAngle = acos((sin(lat1)* sin(lat2)) + (cos(lat1)* cos(lat2)* cos(lon2-lon1)))
        return centreAngle * 6371.0
    }


}