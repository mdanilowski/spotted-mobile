package pl.mdanilowski.spotted.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.mdanilowski.spotted.data.db.dao.CityDao
import pl.mdanilowski.spotted.data.db.entity.CityEntity

@Database(entities = [CityEntity::class], version = 1)
abstract class SpottedDB : RoomDatabase() {

    abstract fun citiesDao(): CityDao

    companion object {
        const val DB_NAME = "spotted_mobile.db"
    }
}