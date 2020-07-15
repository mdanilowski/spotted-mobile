package pl.mdanilowski.spotted.util

import android.content.Context
import android.content.SharedPreferences

interface StorageUtil {

    fun getCityId(): Long?

    fun saveCityId(city: Long)
}

class SpottedSimpleStorage constructor(appContext: Context) : StorageUtil {

    var sharedPreferences: SharedPreferences = appContext.getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor = sharedPreferences.edit()

    private val _city = "message"

    override fun getCityId(): Long? {
        return sharedPreferences.getLong(this._city, 0)
    }

    override fun saveCityId(city: Long) {
        editor.putLong(_city, city)
        editor.commit()
    }
}