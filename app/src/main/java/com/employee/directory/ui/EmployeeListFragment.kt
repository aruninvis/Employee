package com.employee.directory.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.employee.directory.adapter.EmployeeAdapter
import com.employee.directory.databinding.EmployeeFragmentListBinding
import com.employee.directory.viewmodels.EmployeeViewModel

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EmployeeListFragment : Fragment() {
    private val viewModel: EmployeeViewModel by viewModels()
    lateinit var binding : EmployeeFragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         binding = EmployeeFragmentListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = EmployeeAdapter()
        binding.plantList.adapter = adapter
        subscribeUi(adapter)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(key: String?): Boolean {
                if(key?.length!!>=1) {
                    viewModel.search(key).observe(viewLifecycleOwner) { data ->
                        binding.progressBar.visibility = View.GONE
                        print( data)
                        adapter.submitList(data)
                    }
                }
                return false
            }

            override fun onQueryTextChange(key: String?): Boolean {
                if(key?.length!!<1) {
                    viewModel.getAllEmployees().observe(viewLifecycleOwner) { data ->
                        binding.progressBar.visibility = View.GONE
                        print( data)
                        adapter.submitList(data)
                    }
                }
                return false
            }

        })

        return binding.root
    }

    private fun subscribeUi(adapter: EmployeeAdapter) {
        viewModel.getAllEmployees().observe(viewLifecycleOwner) { data ->
            binding.progressBar.visibility = View.GONE
           print( data)
           adapter.submitList(data)
        }

        viewModel.employeeList.observe(viewLifecycleOwner) { data ->
            binding.progressBar.visibility = View.GONE

            adapter.submitList(data)
        }


    }
}