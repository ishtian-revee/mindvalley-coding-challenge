package com.mindvalley.mindvalleyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mindvalley.mindvalleyapp.data.model.CategoryEntity
import com.mindvalley.mindvalleyapp.data.model.ChannelEntity
import com.mindvalley.mindvalleyapp.data.model.MediaEntity

@Database(
    entities = [MediaEntity::class, ChannelEntity::class, CategoryEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun channelDao(): ChannelDao
}