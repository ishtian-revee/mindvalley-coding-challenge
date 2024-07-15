package com.mindvalley.mindvalleyapp.data.local.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mindvalley.mindvalleyapp.data.model.ChannelNameEntity
import com.mindvalley.mindvalleyapp.data.model.CoverAssetEntity
import com.mindvalley.mindvalleyapp.data.model.IconAssetEntity
import com.mindvalley.mindvalleyapp.data.model.LatestMediaEntity
import com.mindvalley.mindvalleyapp.data.model.SeriesEntity

class Converters {

    @TypeConverter
    fun fromCoverAssetEntity(cover: CoverAssetEntity?): String? {
        val type = object : TypeToken<CoverAssetEntity>() {}.type
        return Gson().toJson(cover, type)
    }

    @TypeConverter
    fun toCoverAssetEntity(str: String?): CoverAssetEntity? {
        val type = object : TypeToken<CoverAssetEntity>() {}.type
        return Gson().fromJson<CoverAssetEntity>(str, type)
    }

    @TypeConverter
    fun fromIconAssetEntity(cover: IconAssetEntity?): String? {
        val type = object : TypeToken<IconAssetEntity>() {}.type
        return Gson().toJson(cover, type)
    }

    @TypeConverter
    fun toIconAssetEntity(str: String?): IconAssetEntity? {
        val type = object : TypeToken<IconAssetEntity>() {}.type
        return Gson().fromJson<IconAssetEntity>(str, type)
    }

    @TypeConverter
    fun fromChannelNameEntity(cover: ChannelNameEntity?): String? {
        val type = object : TypeToken<ChannelNameEntity>() {}.type
        return Gson().toJson(cover, type)
    }

    @TypeConverter
    fun toChannelNameEntity(str: String?): ChannelNameEntity? {
        val type = object : TypeToken<ChannelNameEntity>() {}.type
        return Gson().fromJson<ChannelNameEntity>(str, type)
    }

    @TypeConverter
    fun fromSeriesList(seriesList: List<SeriesEntity?>?): String? {
        val type = object : TypeToken<List<SeriesEntity>>() {}.type
        return Gson().toJson(seriesList, type)
    }

    @TypeConverter
    fun toSeriesList(seriesString: String?): List<SeriesEntity>? {
        val type = object : TypeToken<List<SeriesEntity>>() {}.type
        return Gson().fromJson<List<SeriesEntity>>(seriesString, type)
    }

    @TypeConverter
    fun fromMediaList(mediaList: List<LatestMediaEntity?>?): String? {
        val type = object : TypeToken<List<LatestMediaEntity>>() {}.type
        return Gson().toJson(mediaList, type)
    }

    @TypeConverter
    fun toMediaList(mediaString: String?): List<LatestMediaEntity>? {
        val type = object : TypeToken<List<LatestMediaEntity>>() {}.type
        return Gson().fromJson<List<LatestMediaEntity>>(mediaString, type)
    }

    @TypeConverter
    fun fromLatestMediaEntity(cover: LatestMediaEntity?): String? {
        val type = object : TypeToken<LatestMediaEntity>() {}.type
        return Gson().toJson(cover, type)
    }

    @TypeConverter
    fun toLatestMediaEntity(str: String?): LatestMediaEntity? {
        val type = object : TypeToken<LatestMediaEntity>() {}.type
        return Gson().fromJson<LatestMediaEntity>(str, type)
    }

    @TypeConverter
    fun fromSeriesEntity(cover: SeriesEntity?): String? {
        val type = object : TypeToken<SeriesEntity>() {}.type
        return Gson().toJson(cover, type)
    }

    @TypeConverter
    fun toSeriesEntity(str: String?): SeriesEntity? {
        val type = object : TypeToken<SeriesEntity>() {}.type
        return Gson().fromJson<SeriesEntity>(str, type)
    }

}