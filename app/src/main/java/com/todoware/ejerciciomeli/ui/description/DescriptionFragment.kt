package com.todoware.ejerciciomeli.ui.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.squareup.picasso.Picasso
import com.todoware.ejerciciomeli.R
import com.todoware.ejerciciomeli.databinding.FragmentDescriptionBinding
import com.todoware.ejerciciomeli.ui.resultsDashboard.ResultsViewModel
import com.todoware.ejerciciomeli.utils.ItemCondition.Companion.getConditionByName
import java.text.NumberFormat

class DescriptionFragment : Fragment() {

    private val resultsViewModel: ResultsViewModel by activityViewModels()
    private lateinit var binding: FragmentDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescriptionBinding.inflate(inflater, null, false)

        // observe the data if changed
        resultsViewModel.selected.value?.let {
            Picasso.get().load(it.thumbnail).into(binding.itemDescriptionImg)
            binding.descriptionTitleText.text = it.title
            binding.itemDescriptionPriceText.text =
                NumberFormat.getCurrencyInstance().format(it.price ?: 0)
            binding.itemDescriptionConditionText.text =
                getText(getConditionByName(it.condition.trim()))

            binding.itemDescriptionDeliveryText.text =
                if (it.shipping.free_shipping) getText(R.string.yes) else getText(R.string.no)
            var location = "${ it.seller_address.city.name},  ${ it.seller_address.state.name} "
            binding.itemDescriptionLocationText.text = location

        }

        return binding.root
    }

}
