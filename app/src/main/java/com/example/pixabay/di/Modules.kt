package com.example.pixabay.di

import com.example.pixabay.base.viewmodel.BaseViewModel
import com.example.pixabay.fragment.home.HomeViewModel
import com.example.pixabay.fragment.login.LoginViewModel
import com.example.pixabay.fragment.registration.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { BaseViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegistrationViewModel(get()) }
}
