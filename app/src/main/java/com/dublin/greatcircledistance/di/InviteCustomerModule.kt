package com.dublin.greatcircledistance.di

import com.dublin.greatcircledistance.domain.CustomerListRepository
import com.dublin.greatcircledistance.domain.CustomerListRepositoryImpl
import com.dublin.greatcircledistance.viewmodel.InviteCustomerViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val inviteCustomerModule = module{

    viewModel { InviteCustomerViewModel(get()) }

    single<CustomerListRepository> {
        CustomerListRepositoryImpl(get())
    }

}