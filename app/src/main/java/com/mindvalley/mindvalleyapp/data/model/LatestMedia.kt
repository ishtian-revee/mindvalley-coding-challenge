package com.mindvalley.mindvalleyapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LatestMedia(
    val coverAsset: CoverAsset,
    @PrimaryKey val title: String,
    val type: String
)