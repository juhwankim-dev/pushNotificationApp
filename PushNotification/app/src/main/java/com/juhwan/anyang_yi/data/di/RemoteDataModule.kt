package com.juhwan.anyang_yi.data.di

import com.juhwan.anyang_yi.data.api.*
import com.juhwan.anyang_yi.data.repository.ari.AriRemoteDataSource
import com.juhwan.anyang_yi.data.repository.ari.AriRemoteDataSourceImpl
import com.juhwan.anyang_yi.data.repository.kakao.KakaoRemoteDataSource
import com.juhwan.anyang_yi.data.repository.kakao.KakaoRemoteDataSourceImpl
import com.juhwan.anyang_yi.data.repository.nonsubject.NonsubjectRemoteDataSource
import com.juhwan.anyang_yi.data.repository.nonsubject.NonsubjectRemoteDataSourceImpl
import com.juhwan.anyang_yi.data.repository.schedule.ScheduleRemoteDataSource
import com.juhwan.anyang_yi.data.repository.schedule.ScheduleRemoteDataSourceImpl
import com.juhwan.anyang_yi.data.repository.univ.UnivRemoteDataSource
import com.juhwan.anyang_yi.data.repository.univ.UnivRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {
    @Provides
    @Singleton
    fun provideAriRemoteDataSource(ariApi: AriApi): AriRemoteDataSource {
        return AriRemoteDataSourceImpl(ariApi)
    }

    @Provides
    @Singleton
    fun provideKakaoRemoteDataSource(kakaoApi: KakaoApi): KakaoRemoteDataSource {
        return KakaoRemoteDataSourceImpl(kakaoApi)
    }

    @Provides
    @Singleton
    fun provideNonsubjectRemoteDataSource(nonsubjectApi: NonsubjectApi): NonsubjectRemoteDataSource {
        return NonsubjectRemoteDataSourceImpl(nonsubjectApi)
    }

    @Provides
    @Singleton
    fun provideScheduleRemoteDataSource(scheduleApi: ScheduleApi): ScheduleRemoteDataSource {
        return ScheduleRemoteDataSourceImpl(scheduleApi)
    }

    @Provides
    @Singleton
    fun provideUnivRemoteDataSource(univApi: UnivApi): UnivRemoteDataSource {
        return UnivRemoteDataSourceImpl(univApi)
    }
}