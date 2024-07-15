package com.mindvalley.mindvalleyapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IconAsset(
    val thumbnailUrl: String,
    val url: String,
    @PrimaryKey val id: Int? = null
)