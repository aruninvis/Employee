package com.employee.directory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.lifecycleScope
import com.employee.directory.databinding.ActivityMainBinding
import com.employee.directory.respository.EmployeeRespository
import com.employee.directory.utils.MyPreference
import com.employee.directory.utils.Utils
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var respository: EmployeeRespository

    @Inject
    lateinit var preference: MyPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        if(!preference.getLoadStatus()) {
            lifecycleScope.launchWhenCreated {
                respository.getEmployeesFromServer()
            }
        }

    }
}