package com.kamran.authenticationflow.di

import com.kamran.authenticationflow.use_cases.AuthenticationUseCases
import com.kamran.authenticationflow.use_cases.ValidateEmail
import com.kamran.authenticationflow.use_cases.ValidatePassword
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AuthenticationUseCasesModule {
    @ViewModelScoped
    @Provides
    fun provideAuthenticationUseCases() =
        AuthenticationUseCases(ValidateEmail(), ValidatePassword())
}