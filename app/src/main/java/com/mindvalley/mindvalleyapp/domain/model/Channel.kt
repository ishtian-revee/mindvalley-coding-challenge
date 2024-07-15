package com.mindvalley.mindvalleyapp.domain.model

data class Channel(
    val coverAsset: CoverAsset?,
    val iconAsset: IconAsset?,
    val latestMedia: List<LatestMedia>?,
    val mediaCount: Int?,
    val seriesList: List<Series>?,
    val slug: String?,
    val title: String?
)
