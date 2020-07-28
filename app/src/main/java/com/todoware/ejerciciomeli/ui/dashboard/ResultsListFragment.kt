package com.todoware.ejerciciomeli.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.todoware.ejerciciomeli.databinding.FragmentResultsBinding
import com.todoware.ejerciciomeli.repository.MercadoLibreRepository
import com.todoware.ejerciciomeli.ui.home.utils.ResultViewModelWrapper
import com.todoware.ejerciciomeli.utils.PaginationListener


class ResultsListFragment : Fragment() {
    val resultsViewModel: ResultsViewModel by viewModels {
        ResultViewModelWrapper(MercadoLibreRepository())
    }
    lateinit var binding: FragmentResultsBinding
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var isLoading = false
        binding = FragmentResultsBinding.inflate(inflater, null, false)
        layoutManager = LinearLayoutManager(context)
        binding.content.addOnScrollListener(object : PaginationListener(layoutManager) {
            override fun isLastPage(): Boolean {
                isLoading = false
                return isLoading
            }

            override fun loadMoreItems() {
                resultsViewModel.loadMore(binding.textDashboard.text as String)

                isLoading = true;
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

        })
        resultsViewModel.searchResultsData.observe(viewLifecycleOwner, Observer { response ->
            binding.textDashboard.text = response?.query.toString()
            if (response != null) {
                context?.let {
                    var recyclerAdapter = ResultRecyclerAdapter(it, response)
                    binding.content.setLayoutManager(layoutManager)
                    binding.content.setAdapter(recyclerAdapter)
                }
            }
        })



        return binding.root
    }

    override fun onStart() {
        super.onStart()
        resultsViewModel.updateSearchQuery("LED CREE")

    }
}