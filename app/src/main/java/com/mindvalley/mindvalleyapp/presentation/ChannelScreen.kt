package com.mindvalley.mindvalleyapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mindvalley.mindvalleyapp.presentation.theme.Typography
import com.mindvalley.mindvalleyapp.R
import com.mindvalley.mindvalleyapp.common.Resource
import com.mindvalley.mindvalleyapp.domain.model.Media
import com.mindvalley.mindvalleyapp.presentation.components.NewEpisodes
import com.mindvalley.mindvalleyapp.presentation.theme.Grey
import com.mindvalley.mindvalleyapp.presentation.theme.LightGrey
import timber.log.Timber

@Composable
fun ChannelScreen(modifier: Modifier = Modifier, viewModel: ChannelViewModel) {
    Column(
        modifier = modifier
            .padding(top = 56.dp)
    ) {
        Text(
            modifier = modifier.padding(start = 20.dp, end = 20.dp),
            text = stringResource(id = R.string.channels),
            style = Typography.headlineLarge,
            color = LightGrey
        )

        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                modifier = modifier.padding(top = 30.dp, start = 20.dp, end = 20.dp),
                text = stringResource(id = R.string.new_episodes),
                style = Typography.headlineMedium,
                color = Grey
            )
            SetupEpisodes(viewModel)
        }
    }
}

@Composable
fun SetupEpisodes(viewModel: ChannelViewModel) {
    var newEpisodes by rememberSaveable {
        mutableStateOf(listOf<Media>())
    }
    val episodes by viewModel.newEpisodeStateFlow.collectAsState()
    when (episodes) {
        is Resource.Loading -> {
            Timber.tag("##_API_DATA").e("Loading episodes")
        }

        is Resource.Success -> {
            newEpisodes = (episodes as Resource.Success<List<Media>>).data!!
        }

        is Resource.Error -> {
            Timber.e((episodes as Resource.Error).message)
        }
    }
    NewEpisodes(episodes = newEpisodes)
}