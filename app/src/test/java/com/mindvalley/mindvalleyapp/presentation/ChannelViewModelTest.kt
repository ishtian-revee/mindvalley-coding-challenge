package com.mindvalley.mindvalleyapp.presentation

import com.google.common.truth.Truth
import com.mindvalley.mindvalleyapp.common.Resource
import com.mindvalley.mindvalleyapp.domain.model.Category
import com.mindvalley.mindvalleyapp.domain.model.Channel
import com.mindvalley.mindvalleyapp.domain.model.ChannelName
import com.mindvalley.mindvalleyapp.domain.model.Media
import com.mindvalley.mindvalleyapp.domain.use_case.GetCategoryUseCase
import com.mindvalley.mindvalleyapp.domain.use_case.GetChannelsUseCase
import com.mindvalley.mindvalleyapp.domain.use_case.GetNewEpisodesUseCase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ChannelViewModelTest {

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThread = newSingleThreadContext("UI thread")

    @Mock
    lateinit var getNewEpisodeUseCase: GetNewEpisodesUseCase

    @Mock
    lateinit var getChannelUseCase: GetChannelsUseCase

    @Mock
    lateinit var getCategoryUseCase: GetCategoryUseCase

    private lateinit var viewModel: ChannelViewModel
    private val episodes = mutableListOf<Media>()
    private val channels = mutableListOf<Channel>()
    private val categories = mutableListOf<Category>()

    @Before
    fun setup() {
        Dispatchers.setMain(mainThread)
        viewModel = ChannelViewModel(getNewEpisodeUseCase, getChannelUseCase, getCategoryUseCase)

        ('a'..'z').forEachIndexed { _, c ->
            episodes.add(
                Media(
                    channel = ChannelName(title = c.toString()),
                    coverAsset = null,
                    title = c.toString(),
                    type = c.toString()
                )
            )

            channels.add(
                Channel(
                    coverAsset = null,
                    iconAsset = null,
                    latestMedia = listOf(),
                    mediaCount = 100,
                    seriesList = listOf(),
                    slug = c.toString(),
                    title = c.toString()
                )
            )

            categories.add(
                Category(
                    name = c.toString()
                )
            )
        }
    }

    @Test
    fun `Get all data, success response`() = runTest(UnconfinedTestDispatcher()) {

        `when`(
            getNewEpisodeUseCase.getNewEpisodes()
        ).thenReturn(
            flowOf(Resource.Success(data = episodes))
        )

        `when`(
            getChannelUseCase.getChannels()
        ).thenReturn(
            flowOf(Resource.Success(data = channels))
        )

        `when`(
            getCategoryUseCase.getCategories()
        ).thenReturn(
            flowOf(Resource.Success(data = categories))
        )

        viewModel.getAllData()

        val episodeJob = launch {
            viewModel.newEpisodeStateFlow.collectLatest {
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

        val channelJob = launch {
            viewModel.channelStateFlow.collectLatest {
                delay(1000)
                assertTrue(it is Resource.Success)
                Truth.assertThat(
                    it.data?.get(0)?.title
                ).isEqualTo("a")
                Truth.assertThat(
                    it.data?.get(25)?.slug
                ).isEqualTo("z")
            }
        }

        val categoryJob = launch {
            viewModel.categoryStateFlow.collectLatest {
                delay(1000)
                assertTrue(it is Resource.Success)
                Truth.assertThat(
                    it.data?.get(2)?.name
                ).isEqualTo("c")
                Truth.assertThat(
                    it.data?.get(15)?.name
                ).isEqualTo("p")
                Truth.assertThat(
                    it.data?.get(23)?.name
                ).isEqualTo("x")
            }
        }

        channelJob.cancel()
        episodeJob.cancel()
        categoryJob.cancel()
    }

    @Test
    fun `Get all data, error response`() = runTest(UnconfinedTestDispatcher()) {

        val episodeError = "ERROR: episode"
        val channelError = "ERROR: channel"
        val categoryError = "ERROR: category"

        `when`(
            getNewEpisodeUseCase.getNewEpisodes()
        ).thenReturn(
            flowOf(Resource.Error(message = episodeError))
        )

        `when`(
            getChannelUseCase.getChannels()
        ).thenReturn(
            flowOf(Resource.Error(message = channelError))
        )

        `when`(
            getCategoryUseCase.getCategories()
        ).thenReturn(
            flowOf(Resource.Error(message = categoryError))
        )

        viewModel.getAllData()

        val episodeJob = launch {
            viewModel.newEpisodeStateFlow.collectLatest {
                delay(1000)
                assertTrue(it is Resource.Success)
                Truth.assertThat(
                    it.message
                ).isEqualTo(episodeError)
            }
        }

        val channelJob = launch {
            viewModel.channelStateFlow.collectLatest {
                delay(1000)
                assertTrue(it is Resource.Success)
                Truth.assertThat(
                    it.message
                ).isEqualTo(channelError)
            }
        }

        val categoryJob = launch {
            viewModel.categoryStateFlow.collectLatest {
                delay(1000)
                assertTrue(it is Resource.Success)
                Truth.assertThat(
                    it.message
                ).isEqualTo(categoryError)
            }
        }

        channelJob.cancel()
        episodeJob.cancel()
        categoryJob.cancel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThread.close()
    }
}