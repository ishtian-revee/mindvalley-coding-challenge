package com.mindvalley.mindvalleyapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.mindvalley.mindvalleyapp.presentation.theme.Typography
import com.mindvalley.mindvalleyapp.R
import com.mindvalley.mindvalleyapp.common.Constants.MAX_ITEM_PER_ROW
import com.mindvalley.mindvalleyapp.common.Resource
import com.mindvalley.mindvalleyapp.domain.model.Category
import com.mindvalley.mindvalleyapp.domain.model.Channel
import com.mindvalley.mindvalleyapp.domain.model.Media
import com.mindvalley.mindvalleyapp.presentation.components.Category
import com.mindvalley.mindvalleyapp.presentation.components.CategoryShimmer
import com.mindvalley.mindvalleyapp.presentation.components.Channel
import com.mindvalley.mindvalleyapp.presentation.components.ChannelShimmer
import com.mindvalley.mindvalleyapp.presentation.components.NewEpisode
import com.mindvalley.mindvalleyapp.presentation.components.NewEpisodeShimmer
import com.mindvalley.mindvalleyapp.presentation.theme.DarkGrey
import com.mindvalley.mindvalleyapp.presentation.theme.Grey
import com.mindvalley.mindvalleyapp.presentation.theme.LightGrey
import com.mindvalley.mindvalleyapp.presentation.util.divider
import timber.log.Timber

@Composable
fun ChannelScreen(modifier: Modifier = Modifier, viewModel: ChannelViewModel) {
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = viewModel::getAllData
    ) {
        Column(
            modifier = modifier
                .padding(top = 56.dp)
        ) {
            Text(
                modifier = modifier
                    .padding(start = 20.dp, end = 20.dp),
                text = stringResource(id = R.string.channels),
                style = Typography.headlineLarge,
                color = LightGrey
            )

            // fetching new episode data
            var episodeList by rememberSaveable {
                mutableStateOf(listOf<Media>())
            }
            val episodes by viewModel.newEpisodeStateFlow.collectAsStateWithLifecycle()
            when (episodes) {
                is Resource.Loading -> Timber.tag("##_API_DATA").e("Loading episodes")

                is Resource.Success -> {
                    Timber.tag("##_API_DATA").e("Episode: Success")
                    episodeList = (episodes as Resource.Success<List<Media>>).data!!
                }

                is Resource.Error -> {
                    Timber.tag("##_API_DATA").e("Episode: Error")
                    Timber.e((episodes as Resource.Error).message)
                }
            }

            val episodeColumn = if (episodeList.count() % MAX_ITEM_PER_ROW == 0) {
                episodeList.count() / MAX_ITEM_PER_ROW
            } else {
                episodeList.count() / MAX_ITEM_PER_ROW + 1
            }

            // fetching channel data
            var channelList by rememberSaveable {
                mutableStateOf(listOf<Channel>())
            }
            val channels by viewModel.channelStateFlow.collectAsStateWithLifecycle()
            when (channels) {
                is Resource.Loading -> Timber.tag("##_API_DATA").e("Loading channels")

                is Resource.Success -> {
                    Timber.tag("##_API_DATA").e("Channel: Success")
                    channelList =
                        (channels as Resource.Success<List<Channel>>).data!!
                }

                is Resource.Error -> {
                    Timber.tag("##_API_DATA").e("Channel: Error")
                    Timber.e((channels as Resource.Error).message)
                }
            }

            // fetching category data
            var categoryList by rememberSaveable {
                mutableStateOf(listOf<Category>())
            }
            val categories by viewModel.categoryStateFlow.collectAsStateWithLifecycle()
            when (categories) {
                is Resource.Loading -> Timber.tag("##_API_DATA").e("Loading categories")

                is Resource.Success -> {
                    Timber.tag("##_API_DATA").e("Category: Success")
                    categoryList =
                        (categories as Resource.Success<List<Category>>).data!!
                }

                is Resource.Error -> {
                    Timber.tag("##_API_DATA").e("Category: Error")
                    Timber.e((categories as Resource.Error).message)
                }
            }

            LazyColumn(
                modifier = modifier
                    .padding(top = 8.dp)
                    .fillMaxSize()
            ) {
                item {
                    Text(
                        modifier = modifier.padding(top = 30.dp, start = 20.dp, end = 20.dp),
                        text = stringResource(id = R.string.new_episodes),
                        style = Typography.headlineMedium,
                        color = Grey
                    )
                }

                if (isLoading) {
                    item {
                        NewEpisodeShimmer(modifier = modifier)
                    }
                } else {
                    items(episodeColumn) { index ->
                        NewEpisode(episodes = episodeList, index = index)
                    }
                }

                item {
                    HorizontalDivider(modifier = divider, color = DarkGrey)
                }

                if (isLoading) {
                    item {
                        ChannelShimmer(modifier = modifier)
                    }
                } else {
                    items(channelList.size) { index ->
                        Channel(channel = channelList[index])
                    }
                }

                item {
                    HorizontalDivider(modifier = divider, color = DarkGrey)
                    Text(
                        modifier = modifier
                            .padding(top = 30.dp, start = 20.dp, end = 20.dp),
                        text = stringResource(id = R.string.browse_by_categories),
                        style = Typography.headlineMedium,
                        color = Grey
                    )
                }

                if (isLoading) {
                    item {
                        CategoryShimmer(modifier = modifier)
                    }
                } else {
                    item {
                        Category(categoryList = categoryList)
                    }
                }
            }
        }
    }
}
