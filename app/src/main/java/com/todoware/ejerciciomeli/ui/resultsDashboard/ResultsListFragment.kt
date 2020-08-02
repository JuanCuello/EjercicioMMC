package com.todoware.ejerciciomeli.ui.resultsDashboard

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shouquan.statelayout.StateLayout
import com.todoware.ejerciciomeli.R
import com.todoware.ejerciciomeli.databinding.FragmentResultsBinding
import com.todoware.ejerciciomeli.models.Result
import com.todoware.ejerciciomeli.repository.MercadoLibreRepository
import com.todoware.ejerciciomeli.ui.viewmodel.ResultsViewModel
import com.todoware.ejerciciomeli.utils.PaginationListener
import com.todoware.ejerciciomeli.utils.UiUtils

/**
 * Shows the basic search and a list of the results to the user
 * This view will be the default to the navigation on the activity
 */
const val TAG = "ResultsListFragment"
const val STATE_EMPTY = 3
const val STATE_INITIAL = 4

class ResultsListFragment : Fragment() {
    lateinit var binding: FragmentResultsBinding
    lateinit var layoutManager: LinearLayoutManager
    lateinit var resultsViewModel: ResultsViewModel

    // safeguard to not show results that doesn't match the search
    var searchForValue = ""
    var recyclerAdapter: ResultRecyclerAdapter? = null
    var isLoading = false

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
        recyclerAdapter =
            recyclerAdapter ?: ResultRecyclerAdapter(
                requireContext(),
                this::onItemClick
            )


        binding.content.setLayoutManager(layoutManager)
        binding.content.setAdapter(recyclerAdapter)
        binding.layoutState.setViewForState(STATE_EMPTY, R.layout.state_layout_empty)
        binding.layoutState.setViewForState(STATE_INITIAL, R.layout.state_layout_initial)
        binding.layoutState.setState(STATE_INITIAL)

        startObserveData()
        setScrollListener()

        binding.resultFilterButton.setOnClickListener {
            findNavController().navigate(R.id.action_results_to_filters)
        }

        binding.resultsEditText.doAfterTextChanged {
            UiUtils.editTextDebounce(it, searchForValue, ::startSearch, Handler())
        }

        return binding.root
    }

    // Simple pagination handling
    private fun setScrollListener() {
        binding.content.addOnScrollListener(object : PaginationListener(layoutManager) {
            override fun loadMoreItems() {
                isLoading = true;
                resultsViewModel.loadMore(searchForValue)
                binding.layoutState.setState(StateLayout.STATE_LOADING)
            }

            override fun isLoading(): Boolean {
                return isLoading
            }
        })
    }

    // This livedata came from the repository
    private fun startObserveData() {
        resultsViewModel.searchResultsData.observe(viewLifecycleOwner, Observer { response ->
            var description = "${getText(R.string.currentSearchParameters)}= ${response?.query}"
            Log.d(TAG, "Getting observer response $description")
            binding.resultsTextDescription.text = description

            if (response != null) {

                if (response.results.isEmpty())
                    binding.layoutState.setState(STATE_EMPTY)
                else {
                    // initialize the adapter if null and set the data
                    recyclerAdapter?.let {
                        Looper.getMainLooper().run { binding.layoutState.setState(StateLayout.STATE_CONTENT) }
                        it.addContent(response)
                        it.notifyDataSetChanged()
                        isLoading = false
                        binding.resultFilterButton.isEnabled = true
                    }
                }
            } else {
                binding.layoutState.setState(StateLayout.STATE_ERROR)
            }
        })
    }

    private fun onItemClick(result: Result?) {
        result ?: return
        resultsViewModel.select(result)
        Log.d(TAG, "Open description for item ${result.id}")
        findNavController().navigate(R.id.action_results_to_details)
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
        binding.layoutState.setState(StateLayout.STATE_LOADING)

    }
}