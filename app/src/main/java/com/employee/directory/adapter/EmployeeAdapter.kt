package com.employee.directory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.employee.directory.databinding.ListItemBinding
import com.employee.directory.model.db.EmployeeTable
import com.employee.directory.model.response.EmployeeItem
import com.employee.directory.ui.EmployeeListFragmentDirections


class EmployeeAdapter : ListAdapter<EmployeeTable, RecyclerView.ViewHolder>(TodoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ToDoViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        (holder as ToDoViewHolder).bind(data)
    }


    class ToDoViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.data?.let { data ->
                    navigateToDetail(data, it)
                }
            }
        }

        private fun navigateToDetail(
            data: EmployeeTable,
            view: View
        ) {

          val direction = EmployeeListFragmentDirections.actionEmployeeListFragmentToListDetailFragment(data)
            view.findNavController().navigate(direction)

        }

        fun bind(item: EmployeeTable) {
            binding.apply {
                data = item
                Glide.with(itemView)
                    .load(item.profile_image)
                    .centerCrop()
                    .into(imageView)
                executePendingBindings()
            }
        }
    }



}

private class TodoDiffCallback : DiffUtil.ItemCallback<EmployeeTable>() {

    override fun areItemsTheSame(oldItem: EmployeeTable, newItem: EmployeeTable): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EmployeeTable, newItem: EmployeeTable): Boolean {
        return oldItem == newItem
    }
}