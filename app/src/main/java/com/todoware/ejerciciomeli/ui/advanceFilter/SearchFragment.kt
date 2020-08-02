package com.todoware.ejerciciomeli.ui.advanceFilter

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.todoware.ejerciciomeli.databinding.FragmentHomeBinding
import com.todoware.ejerciciomeli.ui.viewmodel.ResultsViewModel
import com.todoware.ejerciciomeli.utils.UiUtils.editTextDebounce

const val TAG = "SearchFragment"

class SearchFragment : Fragment() {

    private val homeViewModel: ResultsViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding
    private var searchForValue = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Enable back navigation
        val supportActionBar = (requireActivity() as AppCompatActivity).supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = FragmentHomeBinding.inflate(inflater, null, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.searchEditText.doAfterTextChanged {
            editTextDebounce(it.toString().trim(), searchForValue, ::startSearch, Handler())
        }

        homeViewModel.searchResultsData.observe(viewLifecycleOwner, Observer { response ->
            if (response != null) {
                context
                binding.searchTextTitle.text = response.available_filters?.toString()

            }
        })
    }

    private fun startSearch(search: String) {
        searchForValue = search
        homeViewModel.searchQuery(searchForValue)
        Log.d(TAG, "Search triggered: $search, ")
        
    }
}