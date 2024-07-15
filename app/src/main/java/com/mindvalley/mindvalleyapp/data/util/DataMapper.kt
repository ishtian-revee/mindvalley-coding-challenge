package com.mindvalley.mindvalleyapp.data.util

import com.mindvalley.mindvalleyapp.data.model.CategoryEntity
import com.mindvalley.mindvalleyapp.data.model.ChannelEntity
import com.mindvalley.mindvalleyapp.data.model.MediaEntity
import com.mindvalley.mindvalleyapp.domain.model.Category
import com.mindvalley.mindvalleyapp.domain.model.Channel
import com.mindvalley.mindvalleyapp.domain.model.ChannelName
import com.mindvalley.mindvalleyapp.domain.model.CoverAsset
import com.mindvalley.mindvalleyapp.domain.model.IconAsset
import com.mindvalley.mindvalleyapp.domain.model.LatestMedia
import com.mindvalley.mindvalleyapp.domain.model.Media
import com.mindvalley.mindvalleyapp.domain.model.Series

fun List<MediaEntity>.toMedia(): List<Media> {
    return this.map { entry ->
        Media(
            coverAsset = CoverAsset(url = entry.coverAsset?.url),
            title = entry.title,
            type = entry.type,
            channel = ChannelName(title = entry.channel?.title)
        )
    }
}

fun List<ChannelEntity>.toChannel(): List<Channel> {
    return this.map { entry ->
        val latestMedia: MutableList<LatestMedia> = ArrayList()
        val seriesList: MutableList<Series> = ArrayList()

        entry.latestMedia?.forEach {
            latestMedia.add(
                LatestMedia(
                    coverAsset = CoverAsset(url = it.coverAsset?.url),
                    title = it.title,
                    type = it.type
                )
            )
        }
        entry.series?.forEach {
            seriesList.add(
                Series(
                    coverAsset = CoverAsset(it.coverAsset?.url),
                    id = it.id,
                    title = it.title
                )
            )
        }

        Channel(
            coverAsset = CoverAsset(url = entry.coverAsset?.url),
            iconAsset = IconAsset(
                thumbnailUrl = entry.iconAsset?.thumbnailUrl,
                url = entry.iconAsset?.url
            ),
            latestMedia = latestMedia,
            mediaCount = entry.mediaCount,
            seriesList = seriesList,
            slug = entry.slug,
            title = entry.title
        )
    }
}

fun List<CategoryEntity>.toCategory(): List<Category> {
    return this.map { entry ->
        Category(
            name = entry.name
        )
    }
}