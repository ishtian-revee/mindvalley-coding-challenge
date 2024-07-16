package com.mindvalley.mindvalleyapp.domain.use_case

import com.mindvalley.mindvalleyapp.common.Resource
import com.mindvalley.mindvalleyapp.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryUseCase {
    suspend fun getCategories(): Flow<Resource<List<Category>>>
}