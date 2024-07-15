package com.mindvalley.mindvalleyapp.data.remote

import com.mindvalley.mindvalleyapp.common.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class RemoteDataSource(
    private val api: MindvalleyApi
) {
    suspend fun getNewEpisodes() = flow {
        try {
            val episodes = api.getNewEpisodes()
            emit(Resource.Success(episodes))
        } catch (e: Exception) {
            Timber.tag("##_API_ERROR").e(e)
            emit(Resource.Error(e.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getChannels() = flow {
        try {
            val channels = api.getChannels()
            emit(Resource.Success(channels))
        } catch (e: Exception) {
            Timber.tag("##_API_ERROR").e(e)
            emit(Resource.Error(e.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getCategories() = flow {
        try {
            val categories = api.getCategories()
            emit(Resource.Success(categories))
        } catch (e: Exception) {
            Timber.tag("##_API_ERROR").e(e)
            emit(Resource.Error(e.toString()))
        }
    }.flowOn(Dispatchers.IO)
}