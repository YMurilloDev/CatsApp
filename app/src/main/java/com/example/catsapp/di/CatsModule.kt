package com.example.catsapp.di

import com.example.catsapp.data.DefaultCatsRepository
import com.example.catsapp.data.CatsApi
import com.example.catsapp.domain.CatsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object CatsModule {
    @Provides
    fun provideCatsRepository(api: CatsApi): CatsRepository {
        return DefaultCatsRepository(api)
    }

    @Provides
    fun provideCatsApi(retrofit: Retrofit): CatsApi {
        return retrofit.create(CatsApi::class.java)
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                addNetworkInterceptor(
                    Interceptor { chain ->
                        val headers = chain.request().headers.newBuilder()
                            .add("x-api-key", "bda53789-d59e-46cd-9bc4-2936630fde39")
                            .build()
                        val request = chain.request().newBuilder().headers(headers).build()
                        chain.proceed(request)
                    }
                )
            }.build())
            .build()
    }

}
