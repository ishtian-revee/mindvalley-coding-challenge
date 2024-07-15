package com.mindvalley.mindvalleyapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Media(
    val channel: ChannelName,
    val coverAsset: CoverAsset,
    val title: String,
    val type: String,
    @PrimaryKey val id: Int? = null
)