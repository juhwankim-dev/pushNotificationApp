package com.juhwan.anyang_yi.data.di

import com.juhwan.anyang_yi.BuildConfig
import com.juhwan.anyang_yi.data.api.*
import com.juhwan.anyang_yi.present.config.Constants
import com.juhwan.anyang_yi.present.config.Constants.ARI_BASE_URL
import com.juhwan.anyang_yi.present.config.Constants.KAKAO_BASE_URL
import com.juhwan.anyang_yi.present.config.Constants.NONSUBJECT_BASE_URL
import com.juhwan.anyang_yi.present.config.Constants.SCHEDULE_BASE_URL
import com.juhwan.anyang_yi.present.config.Constants.UNIV_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Qualifier
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
    @Named("Ari")
    fun provideAriRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ARI_BASE_URL)
            .client(okHttpClient)
            .client(provideHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Named("Contact")
    fun provideContactRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ARI_BASE_URL)
            .client(okHttpClient)
            .client(provideHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Named("Kakao")
    fun provideKakaoRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(KAKAO_BASE_URL)
            .client(okHttpClient)
            .client(provideHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Named("Nonsubject")
    fun provideNonsubjectRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NONSUBJECT_BASE_URL)
            .client(okHttpClient)
            .client(provideHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Named("Schedule")
    fun provideScheduleRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(SCHEDULE_BASE_URL)
            .client(okHttpClient)
            .client(provideHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Named("Univ")
    fun provideUnivRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(UNIV_BASE_URL)
            .client(okHttpClient)
            .client(provideHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideAriApiService(@Named("Ari") retrofit: Retrofit): AriApi {
        return retrofit.create(AriApi::class.java)
    }

    @Provides
    @Singleton
    fun provideContactApiService(@Named("Contact") retrofit: Retrofit): ContactApi {
        return retrofit.create(ContactApi::class.java)
    }

    @Provides
    @Singleton
    fun provideKakaoApiService(@Named("Kakao") retrofit: Retrofit): KakaoApi {
        return retrofit.create(KakaoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNonsubjectApiService(@Named("Nonsubject") retrofit: Retrofit): NonsubjectApi {
        return retrofit.create(NonsubjectApi::class.java)
    }

    @Provides
    @Singleton
    fun provideScheduleApiService(@Named("Schedule") retrofit: Retrofit): ScheduleApi {
        return retrofit.create(ScheduleApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUnivApiService(@Named("Univ") retrofit: Retrofit): UnivApi {
        return retrofit.create(UnivApi::class.java)
    }

    // 만약 안되면 setLevel 시도해보기
    private fun getLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
            level = HttpLoggingInterceptor.Level.BODY
        }
}