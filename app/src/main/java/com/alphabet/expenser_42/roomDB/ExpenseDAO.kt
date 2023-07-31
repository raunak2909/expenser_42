package com.alphabet.expenser_42.roomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.alphabet.expenser_42.model.ExpenseModel

@Dao
interface ExpenseDAO {

    @Insert
    fun addExpense(newExpense : ExpenseModel)

    @Query("select * from expense")
    fun getAllExpense() : LiveData<List<ExpenseModel>>

}