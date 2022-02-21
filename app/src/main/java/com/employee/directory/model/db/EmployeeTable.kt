package com.employee.directory.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "employee")
data class EmployeeTable (
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name")  val name: String?,
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "profile_image") val profile_image: String?,
    @ColumnInfo(name = "street") val street: String?,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "website") val website: String?,
    ):Serializable{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "taskId")
    var taskId: Long = 0
}
