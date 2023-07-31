package com.alphabet.expenser_42.screen.addTrans.viewModel

import androidx.lifecycle.ViewModel
import com.alphabet.expenser_42.model.ExpenseModel
import com.alphabet.expenser_42.repo.ExpenseRepository

class AddTransactionViewModel(val expenseRepository: ExpenseRepository) : ViewModel(){

    fun addExpense(newExpense : ExpenseModel){
        expenseRepository.addExpense(newExpense)
    }

}