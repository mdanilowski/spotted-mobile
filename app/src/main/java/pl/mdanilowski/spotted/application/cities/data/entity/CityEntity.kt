package pl.mdanilowski.spotted.application.cities.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CITY")
data class CityEntity(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "image") var image: String?
) {
    @PrimaryKey(autoGenerate = true) var id : Long = 0
}