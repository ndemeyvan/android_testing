package com.example.androidtesting.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtesting.Entities.Spend
import com.example.androidtesting.R
import com.example.androidtesting.ViewModel.SpendViewModel

class SpendAdapter(var spendList: List<Spend>,var spendViewModel: SpendViewModel): RecyclerView.Adapter<SpendAdapter.SpendViewHolder>() {


    inner class  SpendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val amount: TextView = itemView.findViewById(R.id.tvSpendAmount)
        val description: TextView= itemView.findViewById(R.id.tvDescription)
        val delete: ImageView = itemView.findViewById(R.id.imDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpendAdapter.SpendViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.spend_layout_item, parent, false)
        return SpendViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpendAdapter.SpendViewHolder, position: Int) {
        var currentElement = spendList[position]
        holder.apply {
            amount.text = currentElement.amount.toString()
            description.text = currentElement.description
            delete.setOnClickListener {
               spendViewModel.deleteSpendItem(currentElement)
            }
        }
    }

    override fun getItemCount()= spendList.size


}