package com.mindvalley.mindvalleyapp.data

import com.mindvalley.mindvalleyapp.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class DataSource<Result, Response> {

    protected abstract fun loadFromDB(): Flow<Result>

    protected abstract fun canCall(): Boolean

    protected abstract suspend fun createCall(): Flow<Resource<Response>>

    protected abstract suspend fun savaData(data: Response?)

    private var result: Flow<Resource<Result>> = flow {
        if (canCall()) {
            when (val apiResponse = createCall().first()) {
                is Resource.Loading -> {
                    emit(Resource.Loading())
                }

                is Resource.Success -> {
                    savaData(apiResponse.data)
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }

                is Resource.Error -> {
                    emit(Resource.Error(apiResponse.message ?: "ERROR"))
                }
            }
        } else {
            emit(Resource.Loading())
            emitAll(loadFromDB().map { Resource.Success(it) })
        }
    }

    fun asFlow(): Flow<Resource<Result>> = result
}