package com.mindvalley.mindvalleyapp.data.remote

import com.mindvalley.mindvalleyapp.data.model.CategoryResponse
import com.mindvalley.mindvalleyapp.data.model.ChannelResponse
import com.mindvalley.mindvalleyapp.data.model.EpisodeResponse
import retrofit2.http.GET

interface MindvalleyApi {
    @GET("z5AExTtw")
    suspend fun getNewEpisodes(): EpisodeResponse

    @GET("Xt12uVhM")
    suspend fun getChannels(): ChannelResponse

    @GET("A0CgArX3")
    suspend fun getCategories(): CategoryResponse
}