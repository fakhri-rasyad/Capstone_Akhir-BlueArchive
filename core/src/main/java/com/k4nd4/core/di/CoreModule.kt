package com.k4nd4.core.di

import androidx.room.Room
import com.k4nd4.core.data.BlueArchiveRepository
import com.k4nd4.core.data.source.local.LocalDataSource
import com.k4nd4.core.data.source.local.room.StudentDatabase
import com.k4nd4.core.data.source.remote.RemoteDataSource
import com.k4nd4.core.data.source.remote.network.ApiService
import com.k4nd4.core.domain.repository.IBlueArchiveRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory {
        get<StudentDatabase>().studentDao()
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            StudentDatabase::class.java,
            "Student.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module{
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-blue-archive.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IBlueArchiveRepository> { BlueArchiveRepository(get(), get()) }
}