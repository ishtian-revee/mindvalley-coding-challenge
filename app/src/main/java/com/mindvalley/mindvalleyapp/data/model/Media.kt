package com.mindvalley.mindvalleyapp.data.model

data class Media(
    val channel: ChannelName,
    val coverAsset: CoverAsset,
    val title: String,
    val type: String
)