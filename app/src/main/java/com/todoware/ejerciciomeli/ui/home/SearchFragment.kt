package com.todoware.ejerciciomeli.ui.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.todoware.ejerciciomeli.databinding.FragmentHomeBinding
import com.todoware.ejerciciomeli.repository.MercadoLibreRepository
import com.todoware.ejerciciomeli.utils.UiUtils.editTextDebouncer


class SearchFragment : Fragment() {

    lateinit var homeViewModel: HomeViewModel
    lateinit var binding: FragmentHomeBinding
    var searchForValue = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
            .apply { setRepository(MercadoLibreRepository()) }
        binding = FragmentHomeBinding.inflate(inflater, null, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.searchEditText.doAfterTextChanged {
            editTextDebouncer(it, searchForValue, ::startSearch,  Handler())
        }

        homeViewModel.searchResultsData.observe(viewLifecycleOwner, Observer { response ->
            if (response != null) {
                context
                binding.searchTextTitle.text = response.available_filters.toString()

            }
        })
    }

    fun startSearch(search: String) {
        searchForValue = search
        homeViewModel.updateSearchQuery(searchForValue)

    }
}