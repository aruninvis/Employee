package com.employee.directory.network


import com.employee.directory.model.response.EmployeeItem
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {

    @GET("5d565297300000680030a986")
    suspend fun getEmployeeList() : Response<List<EmployeeItem?>?>
}