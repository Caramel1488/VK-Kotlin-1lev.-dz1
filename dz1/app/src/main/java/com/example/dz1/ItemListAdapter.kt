package com.example.dz1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dz1.databinding.ItemLayoutBinding

class ItemListAdapter(private val widthInPx: Int) :
    ListAdapter<Int, ItemListAdapter.Holder>(ItemDiffUtilCallback()) {

    class ItemDiffUtilCallback : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }
    }

    class Holder(
        private val widthInPx: Int,
        private val binding: ItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(number: Int) {
            with(binding.numberTextView) {
                layoutParams = ViewGroup.LayoutParams(widthInPx, widthInPx)
                text = number.toString()
                setBackgroundResource(getColorFromNumber(number))
            }
        }

        private fun getColorFromNumber(number: Int): Int {
            return if (number % 2 == 0) {
                R.color.red
            } else {
                R.color.blue
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(widthInPx, binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}