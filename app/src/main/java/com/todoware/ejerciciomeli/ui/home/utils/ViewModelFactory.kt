package com.todoware.ejerciciomeli.ui.home.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.todoware.ejerciciomeli.repository.MercadoLibreRepository
import com.todoware.ejerciciomeli.ui.dashboard.ResultsViewModel
import com.todoware.ejerciciomeli.ui.home.HomeViewModel


// TODO improvement, simplify event more to just a factory
class ResultViewModelWrapper(private val repo: MercadoLibreRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ResultsViewModel(repo) as T
}

class HomeViewModelWrapper(private val repo: MercadoLibreRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        HomeViewModel(repo) as T
}
