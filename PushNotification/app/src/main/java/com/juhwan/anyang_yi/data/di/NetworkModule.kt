package com.juhwan.anyang_yi.data.di

import com.juhwan.anyang_yi.BuildConfig
import com.juhwan.anyang_yi.data.api.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)

        if(BuildConfig.DEBUG) {
            client.addInterceptor(getLoggingInterceptor())
        }

        return client.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .client(provideHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

//    @Provides
//    @Singleton
//    fun provideConverterFactory(): GsonConverterFactory {
//        return GsonConverterFactory.create()
//    }

    @Provides
    @Singleton
    fun provideAriApiService(retrofit: Retrofit): AriApi {
        return retrofit.create(AriApi::class.java)
    }

    @Provides
    @Singleton
    fun provideContactApiService(retrofit: Retrofit): ContactApi {
        return retrofit.create(ContactApi::class.java)
    }

    @Provides
    @Singleton
    fun provideKakaoApiService(retrofit: Retrofit): KakaoApi {
        return retrofit.create(KakaoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNonsubjectApiService(retrofit: Retrofit): NonsubjectApi {
        return retrofit.create(NonsubjectApi::class.java)
    }

    @Provides
    @Singleton
    fun provideScheduleApiService(retrofit: Retrofit): ScheduleApi {
        return retrofit.create(ScheduleApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUnivApiService(retrofit: Retrofit): UnivApi {
        return retrofit.create(UnivApi::class.java)
    }

    // 만약 안되면 setLevel 시도해보기
    private fun getLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
            level = HttpLoggingInterceptor.Level.BODY
        }
}