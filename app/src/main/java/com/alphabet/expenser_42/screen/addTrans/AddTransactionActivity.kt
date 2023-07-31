package com.alphabet.expenser_42.screen.addTrans

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alphabet.expenser_42.R
import com.alphabet.expenser_42.adapter.RecyclerCatTypeAdapter
import com.alphabet.expenser_42.databinding.ActivityAddTransactionBinding
import com.alphabet.expenser_42.databinding.DialogSelectCatTypeBinding
import com.alphabet.expenser_42.model.CategoryModel
import com.alphabet.expenser_42.model.ExpenseModel
import com.alphabet.expenser_42.repo.ExpenseRepository
import com.alphabet.expenser_42.roomDB.AppDatabase
import com.alphabet.expenser_42.screen.addTrans.viewModel.AddTransactionViewModel
import com.alphabet.expenser_42.screen.addTrans.viewModel.AddTransactionViewModelFactory
import com.alphabet.expenser_42.screen.home.MainActivity

class AddTransactionActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddTransactionBinding
    lateinit var addTransactionViewModel: AddTransactionViewModel
    val arrTransType = arrayListOf<String>("Debit", "Credit")
    var selectedTransType = 0
    var selectedCatType = 0
    lateinit var dialogCatType : Dialog
    var balance = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        balance = intent.getDoubleExtra("balance", 0.0)

        //init viewModel
        // (init repo -> init app database(room db))
        val appDatabase = AppDatabase.getDatabase(this)
        val repo = ExpenseRepository(appDatabase)
        addTransactionViewModel = ViewModelProvider(
            this, AddTransactionViewModelFactory(repo)
        )[AddTransactionViewModel::class.java]

        //scope with binding
        with(binding) {

            btnSelectCatType.setOnClickListener {
                dialogCatType = Dialog(this@AddTransactionActivity)
                val bindingDialog = DialogSelectCatTypeBinding.inflate(layoutInflater)
                dialogCatType.setContentView(bindingDialog.root)

                bindingDialog.recyclerCatType.layoutManager = GridLayoutManager(this@AddTransactionActivity, 4)
                        bindingDialog.recyclerCatType.adapter =
                            RecyclerCatTypeAdapter(this@AddTransactionActivity, MainActivity.arrCat)

                dialogCatType.show()

            }

            spinnerTranType.adapter = ArrayAdapter<String>(
                this@AddTransactionActivity,
                android.R.layout.simple_spinner_dropdown_item,
                arrTransType
            )
            spinnerTranType.onItemSelectedListener = object : OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                    selectedTransType = pos
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }

            //adding transaction
            btnAddTransaction.setOnClickListener {

                var updatedBalance = 0.0

                if(selectedTransType==0){
                    updatedBalance = balance-edtAmt.text.toString().toDouble()
                } else {
                    updatedBalance = balance+edtAmt.text.toString().toDouble()
                }

                addTransactionViewModel.addExpense(
                        ExpenseModel(
                            id = 0,
                            title = edtTitle.text.toString(),
                            desc = edtDesc.text.toString(),
                            amount = edtAmt.text.toString().toDouble(),
                            type = selectedTransType,
                            balance = updatedBalance,
                            catType = selectedCatType,
                            date = System.currentTimeMillis().toString()
                        )
                    )
            }


        }
    }

    fun onCatTypeSelected(catModel: CategoryModel){
        selectedCatType = catModel.id
        binding.imgCatType.setImageResource(catModel.imgPath)
        binding.txtCatType.text = catModel.name
        dialogCatType.dismiss()
        binding.imgCatType.visibility = View.VISIBLE
    }
}