package com.example.androidtesting

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.androidtesting.Database.SpendDataBase
import com.example.androidtesting.Entities.Spend
import com.example.androidtesting.Repository.SpendRepository
import com.example.androidtesting.ViewModel.SpendViewModel
import com.example.androidtesting.ViewModel.SpendViewModelFactory


class SpendFragment : Fragment() {

    lateinit var etAmount: EditText
    lateinit var etDescription: EditText
    lateinit var btSpend: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_spend, container, false)
        btSpend = view.findViewById(R.id.btSpend)
        etDescription = view.findViewById(R.id.etDescription)
        etAmount = view.findViewById(R.id.etAmount)
        val context = view.context;
        val database = SpendDataBase(context)
        val repository = SpendRepository(database)
        val factory = SpendViewModelFactory(repository)
        //Quand on utilise la delegation ,
        // pour passer une valeur au factory , on l efait comme ici bas
        val spendViewModel: SpendViewModel by activityViewModels() { factory }

        btSpend.setOnClickListener {
            if (!(etAmount.text.toString().toInt() <= 0 || etDescription.text.isEmpty())) {
                makeToast(context, "Please fill the form correctly")
            } else {
                var spend = Spend(etAmount.text.toString().toInt(), etDescription.text.toString())
                spendViewModel.insertSpendItem(spend)
                makeToast(context, "Spend added successfuly")
            }
        }
        return view
    }

    private fun makeToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

}