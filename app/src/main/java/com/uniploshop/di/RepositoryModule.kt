package com.uniploshop.di

import android.content.Context
import com.uniploshop.repository.AuthPreferenceRepository
import com.uniploshop.repository.AuthPreferenceRepositoryImpl
import com.uniploshop.repository.CartRepository
import com.uniploshop.repository.CartRepositoryImpl
import com.uniploshop.repository.LoginRepository
import com.uniploshop.repository.LoginRepositoryImpl
import com.uniploshop.repository.ProductRepository
import com.uniploshop.repository.ProductRepositoryImpl
import com.uniploshop.repository.UserRepository
import com.uniploshop.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideContext(): Context {
        return context.applicationContext
    }

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthPreferenceRepository {
        return AuthPreferenceRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(): LoginRepository {
        return LoginRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideProductRepository(): ProductRepository {
        return ProductRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl(provideAuthRepository())
    }

    @Provides
    @Singleton
    fun provideCartRepository(): CartRepository {
        return CartRepositoryImpl()
    }
}