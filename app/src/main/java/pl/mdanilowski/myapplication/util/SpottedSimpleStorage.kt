package pl.mdanilowski.myapplication.util

import android.content.Context
import android.content.SharedPreferences
import pl.mdanilowski.myapplication.base.SpottedMobileApplication
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
interface StorageUtil {

    fun getCityName(): String?

    fun saveCityName(city: String)
}

class SpottedSimpleStorage @Inject constructor(app: SpottedMobileApplication) : StorageUtil {

    var sharedPreferences: SharedPreferences = app.getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor = sharedPreferences.edit()

    private val _city = "message"

    override fun getCityName(): String? {
        return sharedPreferences.getString(this._city, null)
    }

    override fun saveCityName(city: String) {
        editor.putString(_city, city)
        editor.commit()
    }
}