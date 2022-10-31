package com.example.dz1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.dz1.databinding.MainFragmentLayoutBinding


class MainFragment : Fragment(R.layout.main_fragment_layout) {

    private val binding: MainFragmentLayoutBinding by viewBinding(MainFragmentLayoutBinding::bind)
    private var itemListAdapter: ItemListAdapter by autoCleared()
    private val viewModel: VM by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        binding.addButton.setOnClickListener {
            viewModel.incrementNumber()
        }

        viewModel.numberList.observe(viewLifecycleOwner) {
            itemListAdapter.submitList(it)
        }

    }

    private fun initAdapter() {
        itemListAdapter = ItemListAdapter(getItemWidthInPx())
        with(binding.list) {
            adapter = itemListAdapter
            layoutManager = GridLayoutManager(
                requireContext(), resources.getInteger(R.integer.column_number)
            )
        }
    }

    private fun getItemWidthInPx(): Int {
        val widthPX = requireContext().resources.displayMetrics.widthPixels
        val columnNumber = requireContext().resources.getInteger(
            R.integer.column_number
        )
        return (widthPX/columnNumber)
    }


}