package com.mindvalley.mindvalleyapp.data.repository

import android.content.Context
import com.mindvalley.mindvalleyapp.common.Resource
import com.mindvalley.mindvalleyapp.data.DataSource
import com.mindvalley.mindvalleyapp.data.local.LocalDataSource
import com.mindvalley.mindvalleyapp.data.model.CategoryResponse
import com.mindvalley.mindvalleyapp.data.model.ChannelResponse
import com.mindvalley.mindvalleyapp.data.model.EpisodeResponse
import com.mindvalley.mindvalleyapp.data.remote.RemoteDataSource
import com.mindvalley.mindvalleyapp.data.util.ConnectivityObserver
import com.mindvalley.mindvalleyapp.data.util.toCategory
import com.mindvalley.mindvalleyapp.data.util.toChannel
import com.mindvalley.mindvalleyapp.data.util.toMedia
import com.mindvalley.mindvalleyapp.domain.model.Category
import com.mindvalley.mindvalleyapp.domain.model.Channel
import com.mindvalley.mindvalleyapp.domain.model.Media
import com.mindvalley.mindvalleyapp.domain.repository.ChannelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChannelRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val connectivityObserver: ConnectivityObserver,
    private val context: Context
) : ChannelRepository {
    override suspend fun getNewEpisodes(): Flow<Resource<List<Media>>> =
        object : DataSource<List<Media>, EpisodeResponse>() {
            override fun loadFromDB(): Flow<List<Media>> {
                return localDataSource.getNewEpisodes().map { it.toMedia() }
            }

            override fun canCall(): Boolean {
                return connectivityObserver.isOnline(context)
            }

            override suspend fun createCall(): Flow<Resource<EpisodeResponse>> {
                return remoteDataSource.getNewEpisodes()
            }

            override suspend fun savaData(data: EpisodeResponse) {
                data.data?.let {
                    it.media?.let { media ->
                        localDataSource.deleteAllEpisodes()
                        localDataSource.insertNewEpisodes(media)
                    }
                }
            }
        }.asFlow()

    override suspend fun getChannels(): Flow<Resource<List<Channel>>> =
        object : DataSource<List<Channel>, ChannelResponse>() {
            override fun loadFromDB(): Flow<List<Channel>> {
                return localDataSource.getChannels().map { it.toChannel() }
            }

            override fun canCall(): Boolean {
                return connectivityObserver.isOnline(context)
            }

            override suspend fun createCall(): Flow<Resource<ChannelResponse>> {
                return remoteDataSource.getChannels()
            }

            override suspend fun savaData(data: ChannelResponse) {
                data.data?.let {
                    it.channels?.let { channel ->
                        localDataSource.deleteAllChannels()
                        localDataSource.insertChannels(channel)
                    }
                }
            }
        }.asFlow()

    override suspend fun getCategories(): Flow<Resource<List<Category>>> =
        object : DataSource<List<Category>, CategoryResponse>() {
            override fun loadFromDB(): Flow<List<Category>> {
                return localDataSource.getCategories().map { it.toCategory() }
            }

            override fun canCall(): Boolean {
                return connectivityObserver.isOnline(context)
            }

            override suspend fun createCall(): Flow<Resource<CategoryResponse>> {
                return remoteDataSource.getCategories()
            }

            override suspend fun savaData(data: CategoryResponse) {
                data.data?.let {
                    it.categories?.let { category ->
                        localDataSource.deleteAllCategories()
                        localDataSource.insertCategories(category)
                    }
                }
            }
        }.asFlow()
}