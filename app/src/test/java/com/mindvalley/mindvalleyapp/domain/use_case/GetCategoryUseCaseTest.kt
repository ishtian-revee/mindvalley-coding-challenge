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
class GetCategoryUseCaseTest {

    private lateinit var getCategoryUseCase: GetCategoryUseCase
    private lateinit var fakeChannelRepository: FakeChannelRepository

    @Before
    fun setUp() {
        fakeChannelRepository = FakeChannelRepository()
        getCategoryUseCase = GetCategoryUseCase(fakeChannelRepository)
    }

    @Test
    fun `Get episode data, success response`() = runTest {
        val job = launch {
            getCategoryUseCase.getCategories()
                .collectLatest {
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
        job.join()
        job.cancel()
    }
}