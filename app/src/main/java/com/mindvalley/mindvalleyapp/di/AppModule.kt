package com.mindvalley.mindvalleyapp.di

import android.app.Application
import androidx.room.Room
import com.mindvalley.mindvalleyapp.common.Constants
import com.mindvalley.mindvalleyapp.data.local.LocalDataSource
import com.mindvalley.mindvalleyapp.data.local.room.AppDatabase
import com.mindvalley.mindvalleyapp.data.local.room.ChannelDao
import com.mindvalley.mindvalleyapp.data.remote.MindvalleyApi
import com.mindvalley.mindvalleyapp.data.remote.RemoteDataSource
import com.mindvalley.mindvalleyapp.data.repository.ChannelRepositoryImpl
import com.mindvalley.mindvalleyapp.data.util.ConnectivityObserver
import com.mindvalley.mindvalleyapp.data.util.NetworkUtil
import com.mindvalley.mindvalleyapp.domain.repository.ChannelRepository
import com.mindvalley.mindvalleyapp.domain.use_case.CategoryUseCase
import com.mindvalley.mindvalleyapp.domain.use_case.ChannelUseCase
import com.mindvalley.mindvalleyapp.domain.use_case.GetCategoryUseCase
import com.mindvalley.mindvalleyapp.domain.use_case.GetChannelsUseCase
import com.mindvalley.mindvalleyapp.domain.use_case.GetNewEpisodesUseCase
import com.mindvalley.mindvalleyapp.domain.use_case.NewEpisodeUseCase
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
object AppModule {

    @Provides
    @Singleton
    fun provideMindvalleyApi(): MindvalleyApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient().newBuilder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .build()
            )
            .build()
            .create(MindvalleyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        app: Application
    ): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideChannelDao(appDatabase: AppDatabase): ChannelDao = appDatabase.channelDao()

    @Provides
    @Singleton
    fun provideLocalDataSource(channelDao: ChannelDao): LocalDataSource =
        LocalDataSource(channelDao)

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: MindvalleyApi): RemoteDataSource = RemoteDataSource(api)

    @Provides
    @Singleton
    fun provideNetworkUtil(): ConnectivityObserver = NetworkUtil()

    @Provides
    @Singleton
    fun provideChannelRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        connectivityObserver: ConnectivityObserver,
        app: Application
    ): ChannelRepository {
        return ChannelRepositoryImpl(
            localDataSource, remoteDataSource, connectivityObserver, app
        )
    }

    @Provides
    @Singleton
    fun provideNewEpisodeUseCase(channelRepository: ChannelRepository): NewEpisodeUseCase =
        GetNewEpisodesUseCase(channelRepository)

    @Provides
    @Singleton
    fun provideChannelUseCase(channelRepository: ChannelRepository): ChannelUseCase =
        GetChannelsUseCase(channelRepository)

    @Provides
    @Singleton
    fun provideCategoryUseCase(channelRepository: ChannelRepository): CategoryUseCase =
        GetCategoryUseCase(channelRepository)
}