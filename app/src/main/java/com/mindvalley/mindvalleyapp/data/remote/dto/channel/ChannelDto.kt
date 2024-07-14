package com.mindvalley.mindvalleyapp.data.remote.dto.channel

data class ChannelDto(
    val coverAsset: CoverAssetDto,
    val iconAsset: IconAssetDto,
    val id: String,
    val latestMedia: List<LatestMediaDto>,
    val mediaCount: Int,
    val series: List<SeryDto>,
    val slug: String,
    val title: String
)