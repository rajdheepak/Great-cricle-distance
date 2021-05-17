package com.dublin.greatcircledistance.domain

import com.dublin.greatcircledistance.model.Data
import kotlinx.coroutines.flow.Flow

interface CustomerListRepository {
    fun getCustomers() : Flow<Data>
}