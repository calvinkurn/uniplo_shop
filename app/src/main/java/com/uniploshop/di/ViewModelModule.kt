package com.uniploshop.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uniploshop.di.viewmodel.ViewModelFactory
import com.uniploshop.di.viewmodel.ViewModelKey
import com.uniploshop.ui.viewmodel.CartActivityViewModel
import com.uniploshop.ui.viewmodel.HomeActivityViewModel
import com.uniploshop.ui.viewmodel.LoginActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginActivityViewModel::class)
    abstract fun bindLoginActivityViewModel(viewModel: LoginActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeActivityViewModel::class)
    abstract fun bindHomeActivityViewModel(viewModel: HomeActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartActivityViewModel::class)
    abstract fun bindCartActivityViewModel(viewModel: CartActivityViewModel): ViewModel
}