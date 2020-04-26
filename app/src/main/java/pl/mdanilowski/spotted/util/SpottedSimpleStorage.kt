package pl.mdanilowski.spotted.util

import android.content.Context
import android.content.SharedPreferences
import pl.mdanilowski.spotted.base.SpottedMobileApplication

interface StorageUtil {

    fun getCityName(): String?

    fun saveCityName(city: String)
}

class SpottedSimpleStorage constructor(appContext: Context) : StorageUtil {

    var sharedPreferences: SharedPreferences = appContext.getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE)
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