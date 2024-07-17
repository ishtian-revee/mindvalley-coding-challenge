package com.mindvalley.mindvalleyapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mindvalley.mindvalleyapp.common.Constants.MAX_ITEM_PER_ROW
import com.mindvalley.mindvalleyapp.domain.model.Media
import com.mindvalley.mindvalleyapp.presentation.theme.DarkGrey
import com.mindvalley.mindvalleyapp.presentation.theme.Grey
import com.mindvalley.mindvalleyapp.presentation.theme.Typography
import com.mindvalley.mindvalleyapp.presentation.theme.White
import com.mindvalley.mindvalleyapp.presentation.util.divider
import com.mindvalley.mindvalleyapp.presentation.util.portraitImage
import java.util.Locale

@Composable
fun NewEpisodes(modifier: Modifier = Modifier, episodes: List<Media>) {
    LazyRow(
        contentPadding = PaddingValues(start = 20.dp)
    ) {
        itemsIndexed(episodes) { index, episode ->
            if (index < MAX_ITEM_PER_ROW) {
                NewEpisodeItem(modifier = modifier, episode)
            }
        }
    }
    HorizontalDivider(modifier = divider, color = DarkGrey)
}

@Composable
fun NewEpisodeItem(modifier: Modifier, media: Media) {
    Column(
        modifier = modifier
            .padding(top = 16.dp, end = 20.dp)
            .wrapContentHeight()
    ) {
        AsyncImage(
            modifier = portraitImage.align(Alignment.CenterHorizontally),
            model = media.coverAsset?.url,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Text(
            modifier = modifier
                .width(152.dp)
                .padding(top = 10.dp, start = 4.dp),
            text = media.title ?: "-",
            style = Typography.titleMedium,
            color = White
        )

        media.channel?.title?.uppercase(Locale.getDefault())?.let {
            Text(
                modifier = modifier
                    .width(152.dp)
                    .padding(top = 12.dp, start = 4.dp),
                text = it,
                style = Typography.bodyMedium,
                color = Grey
            )
        }
    }
}