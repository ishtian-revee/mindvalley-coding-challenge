package com.mindvalley.mindvalleyapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IconAssetEntity(
    val thumbnailUrl: String?,
    @PrimaryKey val url: String = ""
)