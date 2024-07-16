package com.mindvalley.mindvalleyapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindvalley.mindvalleyapp.common.Resource
import com.mindvalley.mindvalleyapp.domain.model.Category
import com.mindvalley.mindvalleyapp.domain.model.Channel
import com.mindvalley.mindvalleyapp.domain.model.Media
import com.mindvalley.mindvalleyapp.domain.use_case.CategoryUseCase
import com.mindvalley.mindvalleyapp.domain.use_case.ChannelUseCase
import com.mindvalley.mindvalleyapp.domain.use_case.NewEpisodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChannelViewModel @Inject constructor(
    private val newEpisodeUseCase: NewEpisodeUseCase,
    private val channelUseCase: ChannelUseCase,
    private val categoryUseCase: CategoryUseCase
) : ViewModel() {

    private val _newEpisodeStateFlow = MutableStateFlow<Resource<List<Media>>>(Resource.Loading())
    val newEpisodeStateFlow = _newEpisodeStateFlow.asStateFlow()

    private val _channelStateFlow = MutableStateFlow<Resource<List<Channel>>>(Resource.Loading())
    val channelStateFlow = _channelStateFlow.asStateFlow()

    private val _categoryStateFlow = MutableStateFlow<Resource<List<Category>>>(Resource.Loading())
    val categoryStateFlow = _categoryStateFlow.asStateFlow()

    fun getAllData() {
        viewModelScope.launch {
            supervisorScope {
                launch {
                    newEpisodeUseCase.getNewEpisodes()
                        .catch {
                            Timber.tag("##_ERROR").e(it)
                            _newEpisodeStateFlow.value = Resource.Error(it.message ?: "")
                        }.collect {
                            _newEpisodeStateFlow.value = it
                        }
                }

                launch {
                    channelUseCase.getChannels()
                        .catch {
                            Timber.tag("##_ERROR").e(it)
                            _channelStateFlow.value = Resource.Error(it.message ?: "")
                        }.collect {
                            _channelStateFlow.value = it
                        }
                }

                launch {
                    categoryUseCase.getCategories()
                        .catch {
                            Timber.tag("##_ERROR").e(it)
                            _categoryStateFlow.value = Resource.Error(it.message ?: "")
                        }.collect {
                            _categoryStateFlow.value = it
                        }
                }
            }
        }
    }
}