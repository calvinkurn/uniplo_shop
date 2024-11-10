package com.uniploshop.di

import com.uniploshop.ui.view.CartActivity
import com.uniploshop.ui.view.HomeActivity
import com.uniploshop.ui.view.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(application: UniploShopApplication)
    fun inject(activity: LoginActivity)
    fun inject(activity: HomeActivity)
    fun inject(activity: CartActivity)
}