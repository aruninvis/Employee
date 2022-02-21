package com.employee.directory.respository

import com.employee.directory.db.EmployeeDao
import com.employee.directory.model.db.EmployeeTable
import com.employee.directory.network.NetworkService
import com.employee.directory.utils.MyPreference
import com.employee.directory.utils.Utils
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeeRespository @Inject constructor( private val newsApi: NetworkService, private val employeeDao: EmployeeDao,private val preference: MyPreference) {

    fun getAllEmployees() = employeeDao.getAllEmployee()

    fun search(key:String) = employeeDao.getFilter(key)

    suspend fun getEmployeesFromServer() {
      var response =   newsApi.getEmployeeList()
        preference.setLoadStatus(true)
       response.body()?.forEach {
           employeeDao.insertToDo(EmployeeTable(id = it?.id!!, name = it.name, username = it.username, email = it.email, profile_image = it.profileImage, street = it.address?.street, phone = it.phone, website = it.website))
       }
    }

}