package pl.mdanilowski.spotted.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import pl.mdanilowski.spotted.data.db.entity.CityEntity

@Dao
interface CityDao {

    @Query("SELECT * FROM CITY")
    fun getAllCities() : Single<List<CityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCities(cityEntities: List<CityEntity>) : Completable
}