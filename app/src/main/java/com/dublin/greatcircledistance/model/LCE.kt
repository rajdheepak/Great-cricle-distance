package com.dublin.greatcircledistance.model

/*
 * L - Loading
 * C - Content
 * E - Error
 *
 * pojo class to communicate the view from ViewModel about the current status of data
 */

sealed class LCE {
    object Loading : LCE()
    data class Content(val customers: List<Customer>) : LCE()
    data class Error(val errorMessage: String) : LCE()
}