package com.code.androidcodingchallenge.di


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import com.code.androidcodingchallenge.login.repository.LoginRepository
import com.code.androidcodingchallenge.networking.Api
import com.code.androidcodingchallenge.networking.RemoteDataSource.Companion.BASE_URL
import com.code.androidcodingchallenge.networking.SafeApiRequest
import com.code.androidcodingchallenge.register.repository.RegisterRepository
import com.code.androidcodingchallenge.userpreferences.UserPreferences
import com.code.androidcodingchallenge.utils.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRegisterLoginApi(): Api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)

    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> = context.createDataStore("my_data_store")

    @Singleton
    @Provides
    fun providesAuthRepository(api : Api, preferences: UserPreferences) : SafeApiRequest = RegisterRepository(api, preferences)

    @Singleton
    @Provides
    fun providesLoginRepository(loginApi : Api) : SafeApiRequest = LoginRepository(loginApi)

    @Singleton
    @Provides
    fun providesDispatchers(): DispatcherProvider = object : DispatcherProvider{
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }
}