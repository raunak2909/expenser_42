package com.alphabet.expenser_42.screen.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alphabet.expenser_42.R
import com.alphabet.expenser_42.adapter.RecyclerTransactionAdapter
import com.alphabet.expenser_42.databinding.ActivityMainBinding
import com.alphabet.expenser_42.model.CategoryModel
import com.alphabet.expenser_42.model.ExpenseModel
import com.alphabet.expenser_42.model.TransactionModel
import com.alphabet.expenser_42.repo.ExpenseRepository
import com.alphabet.expenser_42.roomDB.AppDatabase
import com.alphabet.expenser_42.screen.addTrans.AddTransactionActivity
import com.alphabet.expenser_42.screen.home.viewModel.HomeViewModel
import com.alphabet.expenser_42.screen.home.viewModel.HomeViewModelFactory
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    companion object {

        val arrCat = ArrayList<CategoryModel>().apply {
            add(CategoryModel(id = 1, imgPath = R.drawable.cloth, name = "Shopping"))
            add(CategoryModel(id = 2, imgPath = R.drawable.gas, name = "Petrol/Gas"))
            add(CategoryModel(id = 3, imgPath = R.drawable.fast_food, name = "FastFood"))
            add(CategoryModel(id = 4, imgPath = R.drawable.groceries, name = "Groceries"))
            add(CategoryModel(id = 5, imgPath = R.drawable.massage, name = "Massage"))
            add(CategoryModel(id = 6, imgPath = R.drawable.movie, name = "Movie"))
            add(CategoryModel(id = 7, imgPath = R.drawable.salon, name = "Salon"))
            add(CategoryModel(id = 8, imgPath = R.drawable.public_transport, name = "Travel"))
        }



    }

    lateinit var binding: ActivityMainBinding
    lateinit var homeViewModel: HomeViewModel
    var balance = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appDb = AppDatabase.getDatabase(this)
        val repo = ExpenseRepository(appDb)
        homeViewModel =
            ViewModelProvider(this, HomeViewModelFactory(repo))[HomeViewModel::class.java]

        with(binding) {


            homeViewModel.getAllExpense().observe(this@MainActivity) { expenses ->
                if (expenses.isNotEmpty()) {
                    getAllDayWiseExpense(expenses as ArrayList<ExpenseModel>)
                }

            }

            btnAddTransaction.setOnClickListener {
                startActivity(Intent(this@MainActivity, AddTransactionActivity::class.java)
                    .putExtra("balance", balance))
            }


        }
    }

    fun getAllDayWiseExpense(allExpenses : ArrayList<ExpenseModel>){
        allExpenses.reverse()
        balance = allExpenses[0].balance
        binding.txtTotalBalance.text = balance.toString()
        var arrTransactions = ArrayList<TransactionModel>()

        var arrUniqueDates = ArrayList<String>()

        for (expense in allExpenses){
           val date = getFormatedDate(expense.date.toLong())


            if(!arrUniqueDates.contains(date)){
                arrUniqueDates.add(date)
            }


        }

        Log.d("dates", arrUniqueDates.toString())

        for (eachDate in arrUniqueDates){

            var totalDayExpense = 0.0

            var eachDateTrans = ArrayList<ExpenseModel>()
            for (expense in allExpenses){
                val expenseDate = getFormatedDate(expense.date.toLong())
                if(expenseDate==eachDate){
                    eachDateTrans.add(expense)
                    if(expense.type==0){
                        totalDayExpense += expense.amount;
                    } else {
                        totalDayExpense -= expense.amount;
                    }
                }
            }

            val eachDateTransactionModel = TransactionModel(eachDate, totalDayExpense.toString(), eachDateTrans)
            arrTransactions.add(eachDateTransactionModel)
        }

        binding.recyclerTransactions.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.recyclerTransactions.adapter =
            RecyclerTransactionAdapter(this@MainActivity, arrTransactions)
        for (trans in arrTransactions){
            Log.d("Transactions", trans.arrExpense.size.toString())
        }






    }

    fun getFormatedDate(timeMillies: Long):String{
        val cal = Calendar.getInstance()
        cal.timeInMillis = timeMillies

        val day = cal.get(Calendar.DAY_OF_MONTH)
        val month = cal.get(Calendar.MONTH)
        val year = cal.get(Calendar.YEAR)
        val date = "$day-$month-$year"
        return date;
    }
}