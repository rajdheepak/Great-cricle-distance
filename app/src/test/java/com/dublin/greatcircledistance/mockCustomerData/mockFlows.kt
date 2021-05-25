package com.dublin.greatcircledistance.mockCustomerData

import com.dublin.greatcircledistance.model.Customer
import com.dublin.greatcircledistance.model.Data
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.io.File

private val mockCustomerFile = File("src/test/java/com/dublin/greatcircledistance/mockCustomerData/customers")

private val expectedCustomerFile = File("src/test/java/com/dublin/greatcircledistance/mockCustomerData/expectedcustomers")

private fun mockCustomersList(): MutableList<Customer> {
    val customers: MutableList<Customer> = mutableListOf()
    mockCustomerFile.readLines().forEach {
        customers.add(Gson().fromJson(it, Customer::class.java))
    }
    return customers
}

fun expectedCustomersList(): MutableList<String> {
    val expectedcustomers: MutableList<String> = mutableListOf()
    expectedCustomerFile.readLines().forEach {
        expectedcustomers.add(it)
    }
    return expectedcustomers
}

val goodFlow: Flow<Data> = flowOf(
    Data.Success(
        mockCustomersList()
    )
)

const val sampleError = "sample error"


val errorFlow: Flow<Data> = flowOf(
    Data.Error(sampleError)
)