package com.dublin.greatcircledistance.model

/*
 * Poojo class for domain layer to communicate to the viewModel about the current data fetch status
 */

sealed class Data {
    data class Success(val customers: List<Customer>) : Data()
    data class Error(val errorMessage: String) : Data()
    object Empty : Data()
}