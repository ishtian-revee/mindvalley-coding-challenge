package com.mindvalley.mindvalleyapp.data.remote

import com.google.common.truth.Truth.assertThat
import com.mindvalley.mindvalleyapp.common.Resource
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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoteDataSourceTest {

    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    lateinit var apiService: MindvalleyApi

    private lateinit var episodesResponse: EpisodeResponse
    private lateinit var channelResponse: ChannelResponse
    private lateinit var categoryResponse: CategoryResponse

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        remoteDataSource = RemoteDataSource(apiService)

        // new episodes, channel and category test data
        val mediaEntityData = mutableListOf<MediaEntity>()
        val channelEntityData = mutableListOf<ChannelEntity>()
        val categoryEntityData = mutableListOf<CategoryEntity>()

        ('a'..'z').forEachIndexed { i, c ->
            mediaEntityData.add(
                MediaEntity(
                    channel = ChannelNameEntity(title = c.toString()),
                    coverAsset = null,
                    title = c.toString(),
                    type = c.toString()
                )
            )

            channelEntityData.add(
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

            categoryEntityData.add(
                CategoryEntity(
                    name = c.toString()
                )
            )
        }

        episodesResponse = EpisodeResponse(EpisodeData(mediaEntityData))
        channelResponse = ChannelResponse(ChannelData(channelEntityData))
        categoryResponse = CategoryResponse(CategoryData(categoryEntityData))
    }

    @Test
    fun `Get episode data, success response`() = runTest {

        `when`(apiService.getNewEpisodes()).thenReturn(
            episodesResponse
        )

        val job = launch {
            remoteDataSource.getNewEpisodes()
                .collectLatest {
                    assertTrue(it is Resource.Success)
                    assertThat(
                        it.data?.data?.media?.get(0)?.title
                    ).isEqualTo("a")
                    assertThat(
                        it.data?.data?.media?.get(17)?.channel?.title
                    ).isEqualTo("r")
                    assertThat(
                        it.data?.data?.media?.get(25)?.type
                    ).isEqualTo("z")
                }
        }
        job.join()
        job.cancel()
    }

    @Test
    fun `Get channel data, success response`() = runTest {

        `when`(apiService.getChannels()).thenReturn(
            channelResponse
        )

        val job = launch {
            remoteDataSource.getChannels()
                .collectLatest {
                    assertTrue(it is Resource.Success)
                    assertThat(
                        it.data?.data?.channels?.get(1)?.title
                    ).isEqualTo("b")
                    assertThat(
                        it.data?.data?.channels?.get(24)?.slug
                    ).isEqualTo("y")
                    assertThat(
                        it.data?.data?.channels?.get(11)?.id
                    ).isEqualTo("11")
                }
        }
        job.join()
        job.cancel()
    }

    @Test
    fun `Get category data, success response`() = runTest {

        `when`(apiService.getCategories()).thenReturn(
            categoryResponse
        )

        val job = launch {
            remoteDataSource.getCategories()
                .collectLatest {
                    assertTrue(it is Resource.Success)
                    assertThat(
                        it.data?.data?.categories?.get(2)?.name
                    ).isEqualTo("c")
                    assertThat(
                        it.data?.data?.categories?.get(15)?.name
                    ).isEqualTo("p")
                    assertThat(
                        it.data?.data?.categories?.get(23)?.name
                    ).isEqualTo("x")
                }
        }
        job.join()
        job.cancel()
    }
}