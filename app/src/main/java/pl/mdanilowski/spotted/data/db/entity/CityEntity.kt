package pl.mdanilowski.spotted.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class CityEntity(
    @PrimaryKey(autoGenerate = true) var id : Long?,
    @ColumnInfo(name = "name") var name: String
)