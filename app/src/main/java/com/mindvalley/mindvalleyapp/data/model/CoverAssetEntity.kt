package com.mindvalley.mindvalleyapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CoverAssetEntity(
    @PrimaryKey val url: String = ""
)