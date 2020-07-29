package com.todoware.ejerciciomeli.ui.resultsDashboard

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.todoware.ejerciciomeli.R
import com.todoware.ejerciciomeli.databinding.FragmentResultsBinding
import com.todoware.ejerciciomeli.repository.MercadoLibreRepository
import com.todoware.ejerciciomeli.utils.PaginationListener
import com.todoware.ejerciciomeli.utils.UiUtils

/**
 * Shows the basic search and a list of the results to the user
 * This view will be the default to the navigation on the activity
 */
class ResultsListFragment : Fragment() {

    lateinit var binding: FragmentResultsBinding
    lateinit var layoutManager: LinearLayoutManager
    lateinit var resultsViewModel: ResultsViewModel

    // safeguard to not show results that doesn't match the search
    var searchForValue = ""
    var recyclerAdapter: ResultRecyclerAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // attach this view model to the activity lyfecycle since this response is needed
        // by the others fragments as well

        activity?.let { it ->
            resultsViewModel = ViewModelProvider(it).get(ResultsViewModel::class.java)
                .apply { setRepository(MercadoLibreRepository()) }
        }

        binding = FragmentResultsBinding.inflate(inflater, null, false)
        layoutManager = LinearLayoutManager(context)
        recyclerAdapter = recyclerAdapter ?: ResultRecyclerAdapter(requireContext())


        binding.content.setLayoutManager(layoutManager)
        binding.content.setAdapter(recyclerAdapter)
        var isLoading = false

        binding.content.addOnScrollListener(object : PaginationListener(layoutManager) {

            override fun loadMoreItems() {
                resultsViewModel.loadMore(searchForValue)
                isLoading = true;
            }

            override fun isLoading(): Boolean {
                return isLoading
            }
        })
        resultsViewModel.searchResultsData.observe(viewLifecycleOwner, Observer { response ->
            binding.resultsTextDescription.text = response?.query.toString()
            if (response != null) {

                // initialize the adapter if null and set the data
                recyclerAdapter?.let {
                    it.addContent(response, searchForValue)
                    it.notifyDataSetChanged()
                    isLoading = false

                }
            } else {
                // ErrorView
            }

        })

        binding.resultFilterButton.setOnClickListener {
            findNavController().navigate(R.id.action_b_to_a)
        }

        binding.resultsEditText.doAfterTextChanged {
            UiUtils.editTextDebouncer(it, searchForValue, ::startSearch, Handler())
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        resultsViewModel.searchResultsData.value?.let {
            recyclerAdapter?.addContent(it)
        }
    }

    fun startSearch(search: String) {
        searchForValue = search

        resultsViewModel.searchQuery(searchForValue)

    }
}