package com.mindvalley.mindvalleyapp.domain.model

data class Channel(
    val coverAsset: CoverAsset?,
    val iconAsset: IconAsset?,
    val id: String?,
    val latestMedia: List<LatestMedia>?,
    val mediaCount: Int?,
    val series: List<Sery>?,
    val slug: String?,
    val title: String?
)
