package com.juhwan.anyang_yi.data.di

import com.juhwan.anyang_yi.data.repository.*
import com.juhwan.anyang_yi.data.repository.ari.AriRemoteDataSource
import com.juhwan.anyang_yi.data.repository.contact.ContactRemoteDataSource
import com.juhwan.anyang_yi.data.repository.kakao.KakaoRemoteDataSource
import com.juhwan.anyang_yi.data.repository.nonsubject.NonsubjectRemoteDataSource
import com.juhwan.anyang_yi.data.repository.schedule.ScheduleRemoteDataSource
import com.juhwan.anyang_yi.data.repository.univ.UnivRemoteDataSource
import com.juhwan.anyang_yi.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideAriRepository(ariRemoteDataSource: AriRemoteDataSource): AriRepository {
        return AriRepositoryImpl(ariRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideContactRepository(contactRemoteDataSource: ContactRemoteDataSource): ContactRepository {
        return ContactRepositoryImpl(contactRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideKakaoRepository(kakaoRemoteDataSource: KakaoRemoteDataSource): KakaoRepository {
        return KakaoRepositoryImpl(kakaoRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideNonsubjectRepository(nonsubjectRemoteDataSource: NonsubjectRemoteDataSource): NonsubjectRepository {
        return NonsubjectRepositoryImpl(nonsubjectRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideScheduleRepository(scheduleRemoteDataSource: ScheduleRemoteDataSource): ScheduleRepository {
        return ScheduleRepositoryImpl(scheduleRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideUnivRepository(univRemoteDataSource: UnivRemoteDataSource): UnivRepository {
        return UnivRepositoryImpl(univRemoteDataSource)
    }
}