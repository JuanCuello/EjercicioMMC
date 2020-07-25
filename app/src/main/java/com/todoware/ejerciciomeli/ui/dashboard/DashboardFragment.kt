package com.todoware.ejerciciomeli.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.todoware.ejerciciomeli.R
import com.todoware.ejerciciomeli.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    lateinit var dashboardViewModel: DashboardViewModel
    lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        binding = FragmentDashboardBinding.inflate(inflater, null, false)

        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textDashboard.text = it.toString()
        })
        return binding.root
    }
}