package com.employee.directory.db

import androidx.lifecycle.lifecycleScope
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.employee.directory.di.ApplicationScope
import com.employee.directory.model.db.EmployeeTable
import com.employee.directory.respository.EmployeeRespository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Database(entities = [EmployeeTable::class], version = 2, exportSchema = false)
abstract class EmployeeDataBase : RoomDatabase()  {

    abstract fun employeeDao(): EmployeeDao



    class Callback @javax.inject.Inject constructor(
        private val database: javax.inject.Provider<EmployeeDataBase>,
        @ApplicationScope private val applicationScope: kotlinx.coroutines.CoroutineScope,
    ) : RoomDatabase.Callback()
}