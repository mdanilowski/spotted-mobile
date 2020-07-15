package pl.mdanilowski.spotted.application.cities.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import pl.mdanilowski.spotted.application.cities.data.entity.CityEntity

@Dao
interface CityDao {

    @Query("SELECT * FROM CITY")
    fun getAllCities() : LiveData<List<CityEntity>>

    @Query("SELECT c.name FROM CITY c WHERE c.id == :cityId")
    fun getCityName(cityId: Long) : Single<String>

    @Query("SELECT c.name FROM CITY c WHERE c.id == :cityId")
    fun getCityNameLiveData(cityId: Long) : LiveData<String>

    @Query("DELETE FROM CITY")
    fun deleteAllCities() : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCities(cityEntities: List<CityEntity>) : Completable
}