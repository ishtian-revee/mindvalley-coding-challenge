package com.mindvalley.mindvalleyapp.data.remote.dto.episode

data class MediaDto(
    val channel: ChannelNameDto,
    val coverAsset: CoverAssetDto,
    val title: String,
    val type: String
)