package com.mindvalley.mindvalleyapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mindvalley.mindvalleyapp.R
import com.mindvalley.mindvalleyapp.domain.model.Channel
import com.mindvalley.mindvalleyapp.presentation.theme.Grey
import com.mindvalley.mindvalleyapp.presentation.theme.Typography
import com.mindvalley.mindvalleyapp.presentation.theme.White
import com.mindvalley.mindvalleyapp.presentation.util.icon

@Composable
fun Channel(modifier: Modifier = Modifier, channel: Channel) {
    val isSeries = channel.seriesList.isNullOrEmpty().not()
    ChannelHeaderItem(modifier = modifier, channel = channel, isSeries = isSeries)
}

@Composable
fun ChannelHeaderItem(modifier: Modifier, channel: Channel, isSeries: Boolean) {
    val mediaCount = if (isSeries) {
        "${channel.mediaCount} series"
    } else {
        "${channel.mediaCount} episodes"
    }

    Row(modifier = modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp)) {
        AsyncImage(
            modifier = icon.clip(CircleShape),
            model = channel.iconAsset?.url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.ic_mindvalley),
        )
        Column(modifier = modifier
            .padding(start = 15.dp)
            .wrapContentHeight(),
            verticalArrangement = Arrangement.Center) {
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
fun ChannelItem() {

}