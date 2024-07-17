package com.mindvalley.mindvalleyapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mindvalley.mindvalleyapp.R
import com.mindvalley.mindvalleyapp.common.Constants.MAX_ITEM_PER_ROW
import com.mindvalley.mindvalleyapp.domain.model.Channel
import com.mindvalley.mindvalleyapp.domain.model.LatestMedia
import com.mindvalley.mindvalleyapp.domain.model.Series
import com.mindvalley.mindvalleyapp.presentation.theme.DarkGrey
import com.mindvalley.mindvalleyapp.presentation.theme.Grey
import com.mindvalley.mindvalleyapp.presentation.theme.Typography
import com.mindvalley.mindvalleyapp.presentation.theme.White
import com.mindvalley.mindvalleyapp.presentation.util.divider
import com.mindvalley.mindvalleyapp.presentation.util.icon
import com.mindvalley.mindvalleyapp.presentation.util.landscapeImage
import com.mindvalley.mindvalleyapp.presentation.util.portraitImage

@Composable
fun Channel(modifier: Modifier = Modifier, channel: Channel) {
    val isSeries = channel.seriesList.isNullOrEmpty().not()
    ChannelHeaderItem(modifier = modifier, channel = channel, isSeries = isSeries)

    if (isSeries) {
        channel.seriesList?.let {
            var rowIndex = 0
            val series = channel.seriesList
            val columnCount = if (series.count() % MAX_ITEM_PER_ROW == 0) {
                series.count() / MAX_ITEM_PER_ROW
            } else {
                series.count() / MAX_ITEM_PER_ROW + 1
            }

            repeat(columnCount) {
                val startIndex = rowIndex * MAX_ITEM_PER_ROW
                val endIndex =
                    if ((rowIndex * MAX_ITEM_PER_ROW) + MAX_ITEM_PER_ROW > series.count()) {
                        series.count()
                    } else {
                        (rowIndex * MAX_ITEM_PER_ROW) + MAX_ITEM_PER_ROW
                    }

                val seriesList = series.subList(startIndex, endIndex)
                LazyRow(
                    contentPadding = PaddingValues(start = 20.dp)
                ) {
                    itemsIndexed(seriesList) { index, series ->
                        if (index < MAX_ITEM_PER_ROW) {
                            SeriesItem(modifier = modifier, series)
                        }
                    }
                }
                rowIndex++
            }
        }
    } else {
        var rowIndex = 0
        val latestMedia = channel.latestMedia
        latestMedia?.let {
            val columnCount = if (latestMedia.count() % MAX_ITEM_PER_ROW == 0) {
                latestMedia.count() / MAX_ITEM_PER_ROW
            } else {
                latestMedia.count() / MAX_ITEM_PER_ROW + 1
            }

            repeat(columnCount) {
                val startIndex = rowIndex * MAX_ITEM_PER_ROW
                val endIndex =
                    if ((rowIndex * MAX_ITEM_PER_ROW) + MAX_ITEM_PER_ROW > latestMedia.count()) {
                        latestMedia.count()
                    } else {
                        (rowIndex * MAX_ITEM_PER_ROW) + MAX_ITEM_PER_ROW
                    }

                val mediaList = latestMedia.subList(startIndex, endIndex)
                LazyRow(
                    contentPadding = PaddingValues(start = 20.dp)
                ) {
                    itemsIndexed(mediaList) { index, media ->
                        if (index < MAX_ITEM_PER_ROW) {
                            CourseItem(modifier = modifier, media)
                        }
                    }
                }
                rowIndex++
            }
        }
    }
    HorizontalDivider(modifier = divider, color = DarkGrey)
}

@Composable
fun ChannelHeaderItem(modifier: Modifier, channel: Channel, isSeries: Boolean) {
    val mediaCount = if (isSeries) {
        "${channel.mediaCount} series"
    } else {
        "${channel.mediaCount} episodes"
    }

    Row(
        modifier = modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
    ) {
        AsyncImage(
            modifier = icon.clip(CircleShape),
            model = channel.iconAsset?.url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.ic_mindvalley),
        )
        Column(
            modifier = modifier
                .padding(start = 15.dp)
                .wrapContentHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = modifier,
                text = channel.title ?: "",
                style = Typography.headlineMedium, color = White
            )
            Text(
                modifier = modifier
                    .padding(top = 5.dp),
                text = mediaCount,
                style = Typography.bodyLarge, color = Grey
            )
        }
    }
}

@Composable
fun CourseItem(modifier: Modifier, media: LatestMedia) {
    Column(
        modifier = modifier
            .padding(top = 20.dp, end = 20.dp)
            .wrapContentHeight()
    ) {
        AsyncImage(
            modifier = portraitImage.align(Alignment.CenterHorizontally),
            model = media.coverAsset?.url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.ic_mindvalley)
        )

        Text(
            modifier = modifier
                .width(152.dp)
                .padding(top = 10.dp, start = 4.dp),
            text = media.title ?: "-",
            style = Typography.titleMedium,
            color = White
        )
    }
}

@Composable
fun SeriesItem(modifier: Modifier, series: Series) {
    Column(
        modifier = modifier
            .padding(top = 20.dp, end = 20.dp)
            .wrapContentHeight()
    ) {
        AsyncImage(
            modifier = landscapeImage.align(Alignment.CenterHorizontally),
            model = series.coverAsset?.url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.ic_mindvalley)
        )

        Text(
            modifier = modifier
                .width(320.dp)
                .padding(top = 10.dp, start = 4.dp),
            text = series.title ?: "-",
            style = Typography.titleMedium,
            color = White
        )
    }
}