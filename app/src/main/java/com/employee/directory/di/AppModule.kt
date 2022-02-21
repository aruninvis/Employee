package com.employee.directory.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.employee.directory.db.EmployeeDao
import com.employee.directory.db.EmployeeDataBase
import com.employee.directory.respository.EmployeeRespository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob())
    }

    @Provides
    @Singleton
    fun provideDatabase(application: Application, callback: EmployeeDataBase.Callback
    ): EmployeeDataBase{
        return Room.databaseBuilder(application, EmployeeDataBase::class.java, "news_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()
    }

    @Provides
    fun provideEmployeeDao(appDatabase: EmployeeDataBase): EmployeeDao {
        return appDatabase.employeeDao()
    }

}
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope
