package com.juhwan.anyang_yi.data.di

import com.juhwan.anyang_yi.data.api.AriApi
import com.juhwan.anyang_yi.data.api.UnivApi
import com.juhwan.anyang_yi.data.repository.*
import com.juhwan.anyang_yi.data.repository.ari.AriRemoteDataSource
import com.juhwan.anyang_yi.data.repository.kakao.KakaoRemoteDataSource
import com.juhwan.anyang_yi.data.repository.keyword.KeywordLocalDataSource
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
    fun provideAriRepository(ariApi: AriApi, ariRemoteDataSource: AriRemoteDataSource): AriRepository {
        return AriRepositoryImpl(ariApi, ariRemoteDataSource)
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
    fun provideUnivRepository(univApi: UnivApi, univRemoteDataSource: UnivRemoteDataSource): UnivRepository {
        return UnivRepositoryImpl(univApi, univRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideKeywordRepository(keywordLocalDataSource: KeywordLocalDataSource): KeywordRepository {
        return KeywordRepositoryImpl(keywordLocalDataSource)
    }
}