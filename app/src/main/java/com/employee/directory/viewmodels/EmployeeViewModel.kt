package com.employee.directory.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.employee.directory.model.db.EmployeeTable
import com.employee.directory.respository.EmployeeRespository
import com.employee.directory.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject internal constructor(private val respository: EmployeeRespository) : ViewModel()  {
    var employeeList: MutableLiveData<List<EmployeeTable>> = MutableLiveData()

    fun getAllEmployees() = respository.getAllEmployees()

    fun search(key :String) = respository.search(key)
}