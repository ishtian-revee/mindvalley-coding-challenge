package com.mindvalley.mindvalleyapp.data.local

import com.google.common.truth.Truth
import com.mindvalley.mindvalleyapp.data.local.room.ChannelDao
import com.mindvalley.mindvalleyapp.data.model.CategoryEntity
import com.mindvalley.mindvalleyapp.data.model.ChannelEntity
import com.mindvalley.mindvalleyapp.data.model.ChannelNameEntity
import com.mindvalley.mindvalleyapp.data.model.MediaEntity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
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
class LocalDataSourceTest {

    private lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var channelDao: ChannelDao

    private var episodeData = mutableListOf<MediaEntity>()
    private var channelData = mutableListOf<ChannelEntity>()
    private var categoryData = mutableListOf<CategoryEntity>()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        localDataSource = LocalDataSource(channelDao)

        ('a'..'z').forEachIndexed { i, c ->
            episodeData.add(
                MediaEntity(
                    channel = ChannelNameEntity(title = c.toString()),
                    coverAsset = null,
                    title = c.toString(),
                    type = c.toString()
                )
            )

            channelData.add(
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

            categoryData.add(
                CategoryEntity(
                    name = c.toString()
                )
            )
        }
    }

    @Test
    fun `Get episodes from local db, success`() = runTest {

        `when`(channelDao.getNewEpisodes()).thenReturn(
            flowOf(episodeData)
        )

        val job = launch {
            localDataSource.getNewEpisodes()
                .collectLatest {
                    Truth.assertThat(
                        it[0].title
                    ).isEqualTo("a")
                    Truth.assertThat(
                        it[17].channel?.title
                    ).isEqualTo("r")
                    Truth.assertThat(
                        it[25].type
                    ).isEqualTo("z")
                }
        }
        job.join()
        job.cancel()
    }

    @Test
    fun `Insert episode data, success`() = runTest {

        `when`(channelDao.insertNewEpisodes(episodeData)).thenReturn(
            arrayOf(1, 2, 3)
        )

        val job = launch {
            val result = localDataSource.insertNewEpisodes(episodeData)
            assertEquals(result.size, 3)
            assertEquals(result[1], 2)
            Truth.assertThat(
                result[0]
            ).isEqualTo(1)
            Truth.assertThat(
                result[1]
            ).isEqualTo(2)
            Truth.assertThat(
                result[2]
            ).isEqualTo(3)
        }
        job.join()
        job.cancel()
    }

    @Test
    fun `Get channels from local db, success`() = runTest {

        `when`(channelDao.getChannels()).thenReturn(
            flowOf(channelData)
        )

        val job = launch {
            localDataSource.getChannels()
                .collectLatest {
                    Truth.assertThat(
                        it[1].title
                    ).isEqualTo("b")
                    Truth.assertThat(
                        it[24].slug
                    ).isEqualTo("y")
                    Truth.assertThat(
                        it[11].id
                    ).isEqualTo("11")
                }
        }
        job.join()
        job.cancel()
    }

    @Test
    fun `Insert channel data, success`() = runTest {

        `when`(channelDao.insertChannels(channelData)).thenReturn(
            arrayOf(1, 2, 3)
        )

        val job = launch {
            val result = localDataSource.insertChannels(channelData)
            assertEquals(result.size, 3)
            assertEquals(result[1], 2)
            Truth.assertThat(
                result[0]
            ).isEqualTo(1)
            Truth.assertThat(
                result[1]
            ).isEqualTo(2)
            Truth.assertThat(
                result[2]
            ).isEqualTo(3)
        }
        job.join()
        job.cancel()
    }

    @Test
    fun `Get categories from local db, success`() = runTest {

        `when`(channelDao.getCategories()).thenReturn(
            flowOf(categoryData)
        )

        val job = launch {
            localDataSource.getCategories()
                .collectLatest {
                    Truth.assertThat(
                        it[2].name
                    ).isEqualTo("c")
                    Truth.assertThat(
                        it[15].name
                    ).isEqualTo("p")
                    Truth.assertThat(
                        it[23].name
                    ).isEqualTo("x")
                }
        }
        job.join()
        job.cancel()
    }

    @Test
    fun `Insert category data, success`() = runTest {

        `when`(channelDao.insertCategories(categoryData)).thenReturn(
            arrayOf(1, 2, 3)
        )

        val job = launch {
            val result = localDataSource.insertCategories(categoryData)
            assertEquals(result.size, 3)
            assertEquals(result[1], 2)
            Truth.assertThat(
                result[0]
            ).isEqualTo(1)
            Truth.assertThat(
                result[1]
            ).isEqualTo(2)
            Truth.assertThat(
                result[2]
            ).isEqualTo(3)
        }
        job.join()
        job.cancel()
    }
}