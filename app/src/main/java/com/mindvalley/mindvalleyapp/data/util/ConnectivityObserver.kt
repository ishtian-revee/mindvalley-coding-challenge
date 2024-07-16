package com.mindvalley.mindvalleyapp.data.util

import android.content.Context

interface ConnectivityObserver {
    fun isOnline(context: Context): Boolean
}