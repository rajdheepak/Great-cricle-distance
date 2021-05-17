package com.dublin.greatcircledistance.domain

import android.content.Context
import com.dublin.greatcircledistance.R
import com.dublin.greatcircledistance.model.Customer
import com.dublin.greatcircledistance.model.Data
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CustomerListRepositoryImpl(private val context: Context) : CustomerListRepository {

    override fun getCustomers(): Flow<Data> = flow {
        emit(Data.Empty)
        try {
            val inputStream = context.resources.openRawResource(R.raw.customers)
            val customers: MutableList<Customer> = mutableListOf()
            inputStream.bufferedReader().forEachLine {
                customers.add(Gson().fromJson(it, Customer::class.java))
            }
            emit(Data.Success(customers))
        }catch (e: Exception) {
            emit(Data.Error(e.localizedMessage))
        }
    }

}