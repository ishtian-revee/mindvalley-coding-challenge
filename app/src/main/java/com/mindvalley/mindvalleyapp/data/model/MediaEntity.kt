package com.mindvalley.mindvalleyapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MediaEntity(
    val channel: ChannelNameEntity?,
    val coverAsset: CoverAssetEntity?,
    @PrimaryKey val title: String = "",
    val type: String?
)