package com.alphabet.expenser_42.repo

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import com.alphabet.expenser_42.model.ExpenseModel
import com.alphabet.expenser_42.roomDB.AppDatabase

class ExpenseRepository(val appDatabase: AppDatabase) {

    fun addExpense(newExpense : ExpenseModel){
        appDatabase.ExpenseDAO().addExpense(newExpense)
    }


    fun getAllExpense() : LiveData<List<ExpenseModel>>{
        return appDatabase.ExpenseDAO().getAllExpense();
    }
}