package com.alphabet.expenser_42.screen.addTrans.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alphabet.expenser_42.repo.ExpenseRepository

class AddTransactionViewModelFactory(val expenseRepository: ExpenseRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddTransactionViewModel(expenseRepository) as T
    }
}