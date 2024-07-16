package com.mindvalley.mindvalleyapp.presentation.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

val portraitImage = Modifier
    .size(width = 152.dp, height = 228.dp)
    .clip(RoundedCornerShape(10.dp))

val landscapeImage = Modifier
    .size(width = 320.dp, height = 172.dp)
    .clip(RoundedCornerShape(5.dp))

val icon = Modifier
    .size(width = 50.dp, height = 50.dp)

val divider = Modifier
    .fillMaxWidth()
    .width(1.dp)
    .padding(top = 30.dp, start = 10.dp, end = 10.dp)