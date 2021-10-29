package com.example.androidtesting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtesting.Adapter.SpendAdapter
import com.example.androidtesting.Database.SpendDataBase
import com.example.androidtesting.Repository.SpendRepository
import com.example.androidtesting.ViewModel.SpendViewModel
import com.example.androidtesting.ViewModel.SpendViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton


class SpendFragmentList : Fragment() {

    lateinit var  rvSpend:RecyclerView
    lateinit var  tvEmptrySpend:TextView
    lateinit var  fvAddSpend:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_spend_list, container, false)
        rvSpend = view.findViewById(R.id.rvSpend)
        tvEmptrySpend = view.findViewById(R.id.tvEmptrySpend)
        fvAddSpend = view.findViewById(R.id.fvAddSpend)
        activity?.actionBar?.hide()
        val context = view.context;
        val database = SpendDataBase(context)
        val repository = SpendRepository(database)
        val factory = SpendViewModelFactory(repository)
        //Quand on utilise la delegation ,
        // pour passer une valeur au factory , on l efait comme ici bas
        val spendViewModel: SpendViewModel by activityViewModels() { factory }
        var adapter = SpendAdapter(listOf(), spendViewModel)

        rvSpend.layoutManager = LinearLayoutManager(context)
        rvSpend.adapter = adapter
        spendViewModel.getAllSpendItem().observe(this, Observer { spendList->
                if(spendList.isEmpty()){
                    tvEmptrySpend.visibility = View.VISIBLE
                    rvSpend.visibility = View.INVISIBLE
                }else{
                    tvEmptrySpend.visibility = View.INVISIBLE
                    adapter.spendList=spendList
                    adapter.notifyDataSetChanged()
                }
        })

        fvAddSpend.setOnClickListener {
            //Go to add spend screen
            Navigation.findNavController(view).navigate(R.id.action_spendFragmentList_to_spendFragment)
        }

        return view
    }



}