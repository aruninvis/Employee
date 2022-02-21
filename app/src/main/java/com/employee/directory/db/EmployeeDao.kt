package com.employee.directory.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.employee.directory.model.db.EmployeeTable

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(employee: EmployeeTable)

    @Delete
    suspend fun deleteToDo(employee: EmployeeTable)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(todos: List<EmployeeTable?>?)

    @Query("SELECT * FROM employee")
    fun getAllEmployee() : LiveData<List<EmployeeTable>>

//
    @Query("SELECT * FROM employee WHERE  name LIKE  '%' || :key || '%' OR email LIKE '%' || :key || '%'")
    fun getFilter(key:String) : LiveData<List<EmployeeTable>>
}