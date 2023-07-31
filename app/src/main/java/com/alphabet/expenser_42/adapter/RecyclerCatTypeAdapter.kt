package com.alphabet.expenser_42.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alphabet.expenser_42.R
import com.alphabet.expenser_42.databinding.ActivityAddTransactionBinding
import com.alphabet.expenser_42.databinding.CatTypeRowBinding
import com.alphabet.expenser_42.model.CategoryModel
import com.alphabet.expenser_42.screen.addTrans.AddTransactionActivity

class RecyclerCatTypeAdapter(val context: Context, val arrCatType: ArrayList<CategoryModel>)
    : RecyclerView.Adapter<RecyclerCatTypeAdapter.ViewHolder>() {
    class ViewHolder(val binding: CatTypeRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(catModel : CategoryModel){
            binding.cat = catModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CatTypeRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return arrCatType.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.bind(arrCatType[position])

        with(holder.binding) {



            Log.d("check", "$position")
            /*imgCatType.setImageResource(arrCatType[position].imgPath)
            txtCatType.text = arrCatType[position].name*/

            llCatType.setOnClickListener {
                (context as AddTransactionActivity).onCatTypeSelected(arrCatType[position])
            }

        }
    }
}