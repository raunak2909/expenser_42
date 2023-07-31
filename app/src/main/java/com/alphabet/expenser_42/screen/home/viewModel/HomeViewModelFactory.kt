package com.alphabet.expenser_42.screen.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alphabet.expenser_42.repo.ExpenseRepository

class HomeViewModelFactory(val expenseRepository: ExpenseRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(expenseRepository) as T
    }
}