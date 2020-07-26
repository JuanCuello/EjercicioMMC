package com.todoware.ejerciciomeli.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.todoware.ejerciciomeli.databinding.FragmentResultsBinding

class ResultsListFragment : Fragment() {
    lateinit var resultsViewModel: ResultsViewModel
    lateinit var binding: FragmentResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        resultsViewModel = ViewModelProvider(this).get(ResultsViewModel::class.java)
        binding = FragmentResultsBinding.inflate(inflater, null, false)

        resultsViewModel.searchResponse.observe(viewLifecycleOwner, Observer { response ->


            binding.textDashboard.text = response?.query.toString()
            if (response != null) {
                context?.let {
                    var recyclerAdapter = ResultRecyclerAdapter(it, response)
                    binding.content.setLayoutManager(
                        LinearLayoutManager(
                            context,
                            RecyclerView.VERTICAL,
                            false
                        )
                    );
                    binding.content.setAdapter(recyclerAdapter)
                }
            }
        })
        return binding.root
    }
}