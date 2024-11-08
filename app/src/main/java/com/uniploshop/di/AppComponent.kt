package com.uniploshop.di

import com.uniploshop.ui.view.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(application: UniploShopApplication)
    fun inject(activity: LoginActivity)
}