package com.example.dz1

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
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

        Log.d("Test", viewModel.number.toString())

        initAdapter()

        binding.addButton.setOnClickListener {
            viewModel.incrementNumber()
            Log.d("TestIncrement", viewModel.number.toString())
        }

        viewModel.numberList.observe(viewLifecycleOwner) {
            itemListAdapter.submitList(it)
        }

    }

    private fun initAdapter() {
        itemListAdapter = ItemListAdapter()
        with(binding.list) {
            adapter = itemListAdapter
            layoutManager = GridLayoutManager(
                requireContext(), when (resources.configuration.orientation) {
                    Configuration.ORIENTATION_PORTRAIT -> 3
                    Configuration.ORIENTATION_LANDSCAPE -> 4
                    else -> 1
                }
            )
        }
    }
}