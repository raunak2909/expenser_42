package com.alphabet.expenser_42.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alphabet.expenser_42.databinding.ExpenseRowBinding
import com.alphabet.expenser_42.model.ExpenseModel

class RecyclerExpenseAdapter(val context: Context, val arrExpenses: List<ExpenseModel>)
    : RecyclerView.Adapter<RecyclerExpenseAdapter.ViewHolder>() {

    class ViewHolder(val binding: ExpenseRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(model: ExpenseModel){
            binding.expense = model
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ExpenseRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return arrExpenses.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            bind(arrExpenses[position])
        }
    }
}