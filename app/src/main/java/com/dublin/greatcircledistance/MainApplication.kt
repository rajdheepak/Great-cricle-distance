package com.dublin.greatcircledistance

import android.app.Application
import com.dublin.greatcircledistance.di.inviteCustomerModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(inviteCustomerModule)
        }
    }

}