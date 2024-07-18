package com.mindvalley.mindvalleyapp.data.repository

import android.content.Context
import com.google.common.truth.Truth
import com.mindvalley.mindvalleyapp.common.Resource
import com.mindvalley.mindvalleyapp.data.local.LocalDataSource
import com.mindvalley.mindvalleyapp.data.model.CategoryData
import com.mindvalley.mindvalleyapp.data.model.CategoryEntity
import com.mindvalley.mindvalleyapp.data.model.CategoryResponse
import com.mindvalley.mindvalleyapp.data.model.ChannelData
import com.mindvalley.mindvalleyapp.data.model.ChannelEntity
import com.mindvalley.mindvalleyapp.data.model.ChannelNameEntity
import com.mindvalley.mindvalleyapp.data.model.ChannelResponse
import com.mindvalley.mindvalleyapp.data.model.EpisodeData
import com.mindvalley.mindvalleyapp.data.model.EpisodeResponse
import com.mindvalley.mindvalleyapp.data.model.MediaEntity
import com.mindvalley.mindvalleyapp.data.remote.RemoteDataSource
import com.mindvalley.mindvalleyapp.data.util.ConnectivityObserver
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ChannelRepositoryImplTest {

    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    @Mock
    lateinit var connectivityObserver: ConnectivityObserver

    private val context = mock(Context::class.java)
    private lateinit var channelRepositoryImpl: ChannelRepositoryImpl

    private var episodeEntities = mutableListOf<MediaEntity>()
    private var channelEntities = mutableListOf<ChannelEntity>()
    private var categoryEntities = mutableListOf<CategoryEntity>()
    private lateinit var episodesResponse: EpisodeResponse
    private lateinit var channelResponse: ChannelResponse
    private lateinit var categoryResponse: CategoryResponse

    @Before
    fun setup() {
        channelRepositoryImpl =
            ChannelRepositoryImpl(localDataSource, remoteDataSource, connectivityObserver, context)

        ('a'..'z').forEachIndexed { i, c ->
            episodeEntities.add(
                MediaEntity(
                    channel = ChannelNameEntity(title = c.toString()),
                    coverAsset = null,
                    title = c.toString(),
                    type = c.toString()
                )
            )
            channelEntities.add(
                ChannelEntity(
                    coverAsset = null,
                    iconAsset = null,
                    latestMedia = listOf(),
                    id = i.toString(),
                    mediaCount = 10,
                    series = listOf(),
                    slug = c.toString(),
                    title = c.toString()
                )
            )
            categoryEntities.add(
                CategoryEntity(
                    name = c.toString()
                )
            )

            episodesResponse = EpisodeResponse(EpisodeData(episodeEntities))
            channelResponse = ChannelResponse(ChannelData(channelEntities))
            categoryResponse = CategoryResponse(CategoryData(categoryEntities))
        }
    }

    @Test
    fun `Get episode data from database while offline, success`() = runTest {

        `when`(connectivityObserver.isOnline(context)).thenReturn(
            false
        )
        `when`(localDataSource.getNewEpisodes()).thenReturn(
            flowOf(episodeEntities)
        )

        val job = launch {
            channelRepositoryImpl.getNewEpisodes()
                .collectLatest {
                    delay(1000)
                    assertTrue(it is Resource.Success)
                    Truth.assertThat(
                        it.data?.get(0)?.title
                    ).isEqualTo("a")
                    Truth.assertThat(
                        it.data?.get(17)?.channel?.title
                    ).isEqualTo("r")
                    Truth.assertThat(
                        it.data?.get(25)?.type
                    ).isEqualTo("z")
                }
        }
        job.join()
        job.cancel()
    }

    @Test
    fun `Get episode data from remote while online, success`() = runTest {

        `when`(connectivityObserver.isOnline(context)).thenReturn(
            true
        )
        `when`(localDataSource.getNewEpisodes()).thenReturn(
            flowOf(episodeEntities)
        )

        `when`(remoteDataSource.getNewEpisodes()).thenReturn(
            flowOf(Resource.Success(episodesResponse))
        )

        val job = launch {
            channelRepositoryImpl.getNewEpisodes()
                .collectLatest {
                    delay(1000)
                    assertTrue(it is Resource.Success)
                    Truth.assertThat(
                        it.data?.get(0)?.title
                    ).isEqualTo("a")
                    Truth.assertThat(
                        it.data?.get(17)?.channel?.title
                    ).isEqualTo("r")
                    Truth.assertThat(
                        it.data?.get(25)?.type
                    ).isEqualTo("z")
                }
        }
        job.join()
        job.cancel()
    }

    @Test
    fun `Get channel data from database while offline, success`() = runTest {

        `when`(connectivityObserver.isOnline(context)).thenReturn(
            false
        )
        `when`(localDataSource.getChannels()).thenReturn(
            flowOf(channelEntities)
        )

        val job = launch {
            channelRepositoryImpl.getChannels()
                .collectLatest {
                    delay(1000)
                    assertTrue(it is Resource.Success)
                    Truth.assertThat(
                        it.data?.get(0)?.title
                    ).isEqualTo("a")
                    Truth.assertThat(
                        it.data?.get(17)?.slug
                    ).isEqualTo("r")
                }
        }
        job.join()
        job.cancel()
    }

    @Test
    fun `Get channel data from remote while online, success`() = runTest {

        `when`(connectivityObserver.isOnline(context)).thenReturn(
            true
        )
        `when`(localDataSource.getChannels()).thenReturn(
            flowOf(channelEntities)
        )

        `when`(remoteDataSource.getChannels()).thenReturn(
            flowOf(Resource.Success(channelResponse))
        )

        val job = launch {
            channelRepositoryImpl.getChannels()
                .collectLatest {
                    delay(1000)
                    assertTrue(it is Resource.Success)
                    Truth.assertThat(
                        it.data?.get(0)?.title
                    ).isEqualTo("a")
                    Truth.assertThat(
                        it.data?.get(17)?.slug
                    ).isEqualTo("r")
                }
        }
        job.join()
        job.cancel()
    }

    @Test
    fun `Get category data from database while offline, success`() = runTest {

        `when`(connectivityObserver.isOnline(context)).thenReturn(
            false
        )
        `when`(localDataSource.getCategories()).thenReturn(
            flowOf(categoryEntities)
        )

        val job = launch {
            channelRepositoryImpl.getCategories()
                .collectLatest {
                    delay(1000)
                    assertTrue(it is Resource.Success)
                    Truth.assertThat(
                        it.data?.get(0)?.name
                    ).isEqualTo("a")
                    Truth.assertThat(
                        it.data?.get(17)?.name
                    ).isEqualTo("r")
                    Truth.assertThat(
                        it.data?.get(25)?.name
                    ).isEqualTo("z")
                }
        }
        job.join()
        job.cancel()
    }

    @Test
    fun `Get category data from remote while online, success`() = runTest {

        `when`(connectivityObserver.isOnline(context)).thenReturn(
            true
        )
        `when`(localDataSource.getCategories()).thenReturn(
            flowOf(categoryEntities)
        )

        `when`(remoteDataSource.getCategories()).thenReturn(
            flowOf(Resource.Success(categoryResponse))
        )

        val job = launch {
            channelRepositoryImpl.getCategories()
                .collectLatest {
                    delay(1000)
                    assertTrue(it is Resource.Success)
                    Truth.assertThat(
                        it.data?.get(0)?.name
                    ).isEqualTo("a")
                    Truth.assertThat(
                        it.data?.get(17)?.name
                    ).isEqualTo("r")
                    Truth.assertThat(
                        it.data?.get(25)?.name
                    ).isEqualTo("z")
                }
        }
        job.join()
        job.cancel()
    }
}