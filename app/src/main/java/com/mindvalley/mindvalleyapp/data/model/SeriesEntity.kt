package com.mindvalley.mindvalleyapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SeriesEntity(
    val coverAsset: CoverAssetEntity?,
    @PrimaryKey val id: String = "",
    val title: String?
)