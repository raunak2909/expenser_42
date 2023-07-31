package com.alphabet.expenser_42.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alphabet.expenser_42.databinding.ExpenseRowBinding
import com.alphabet.expenser_42.databinding.TransactionRowBinding
import com.alphabet.expenser_42.model.ExpenseModel
import com.alphabet.expenser_42.model.TransactionModel

class RecyclerTransactionAdapter(val context: Context, val arrTransactions: List<TransactionModel>)
    : RecyclerView.Adapter<RecyclerTransactionAdapter.ViewHolder>() {

    class ViewHolder(val binding: TransactionRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(model: TransactionModel, context: Context){
            binding.transaction = model
            binding.recyclerArrExpenses.layoutManager = LinearLayoutManager(context)
            binding.recyclerArrExpenses.adapter = RecyclerExpenseAdapter(context, model.arrExpense)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TransactionRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return arrTransactions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            bind(arrTransactions[position], context)
        }
    }
}