package com.mindvalley.mindvalleyapp.domain.use_case

import com.google.common.truth.Truth
import com.mindvalley.mindvalleyapp.common.Resource
import com.mindvalley.mindvalleyapp.domain.repository.FakeChannelRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetNewEpisodesUseCaseTest {

    private lateinit var getNewEpisodesUseCase: GetNewEpisodesUseCase
    private lateinit var fakeChannelRepository: FakeChannelRepository

    @Before
    fun setUp() {
        fakeChannelRepository = FakeChannelRepository()
        getNewEpisodesUseCase = GetNewEpisodesUseCase(fakeChannelRepository)
    }

    @Test
    fun `Get episode data, success response`() = runTest {
        val job = launch {
            getNewEpisodesUseCase.getNewEpisodes()
                .collectLatest {
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
}