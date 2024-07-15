package com.mindvalley.mindvalleyapp.domain.model

data class Media(
    val channel: ChannelName?,
    val coverAsset: CoverAsset?,
    val title: String?,
    val type: String?
)
