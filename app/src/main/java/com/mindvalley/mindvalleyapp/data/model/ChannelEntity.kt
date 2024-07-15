package com.mindvalley.mindvalleyapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChannelEntity(
    val coverAsset: CoverAssetEntity?,
    val iconAsset: IconAssetEntity?,
    val id: String?,
    val latestMedia: List<LatestMediaEntity>?,
    val mediaCount: Int?,
    val series: List<SeriesEntity>?,
    val slug: String?,
    @PrimaryKey val title: String = ""
)