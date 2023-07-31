package com.alphabet.expenser_42.screen.home.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Insert
import androidx.room.Query
import com.alphabet.expenser_42.model.ExpenseModel
import com.alphabet.expenser_42.model.TransactionModel
import com.alphabet.expenser_42.repo.ExpenseRepository
import java.text.DateFormat
import java.util.Calendar

class HomeViewModel(val expenseRepository: ExpenseRepository) : ViewModel() {
    var arrTransactions = MutableLiveData<TransactionModel>()

    fun getAllExpense() : LiveData<List<ExpenseModel>>{
        return expenseRepository.getAllExpense()
    }


}