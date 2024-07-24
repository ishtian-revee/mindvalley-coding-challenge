package com.mindvalley.mindvalleyapp.domain.use_case

import com.mindvalley.mindvalleyapp.common.Resource
import com.mindvalley.mindvalleyapp.domain.model.Category
import com.mindvalley.mindvalleyapp.domain.repository.ChannelRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    private val channelRepository: ChannelRepository
) : CategoryUseCase {
    override suspend fun getCategories(): Flow<Resource<List<Category>>> =
        channelRepository.getCategories()
}