package pl.mdanilowski.spotted.application.cities.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(val name: String, val image: String?) : Parcelable