package com.mindvalley.mindvalleyapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LatestMediaEntity(
    val coverAsset: CoverAssetEntity?,
    @PrimaryKey val title: String = "",
    val type: String?
)