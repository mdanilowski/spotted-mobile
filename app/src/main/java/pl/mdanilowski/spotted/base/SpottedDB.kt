package pl.mdanilowski.spotted.base

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import pl.mdanilowski.spotted.application.cities.data.dao.CityDao
import pl.mdanilowski.spotted.application.cities.data.entity.CityEntity
import pl.mdanilowski.spotted.application.posts.data.dao.PostDao
import pl.mdanilowski.spotted.application.posts.data.entity.CommentEntity
import pl.mdanilowski.spotted.application.posts.data.entity.PostEntity
import pl.mdanilowski.spotted.application.posts.data.entity.PostTagCrossRef
import pl.mdanilowski.spotted.application.posts.data.entity.TagEntity
import java.util.*

@Database(entities = [CityEntity::class, PostEntity::class, PostTagCrossRef::class, TagEntity::class, CommentEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverters::class)
abstract class SpottedDB : RoomDatabase() {

    abstract fun cityDao(): CityDao
    abstract fun postDao(): PostDao

    companion object {
        const val DB_NAME = "spotted_mobile.db"
    }
}

class DateConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}