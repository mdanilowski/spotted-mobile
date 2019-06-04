package pl.mdanilowski.myapplication.util

import android.content.Context
import javax.inject.Inject

interface StringProvider {
    fun getString(textId: Int, vararg objects: Any): String
    fun getQuantityString(textId: Int, count: Int, vararg objects: Any): String
}

class ContextStringProvider @Inject constructor(private val context: Context) : StringProvider {

    override fun getString(textId: Int, vararg objects: Any) = context.resources.getString(textId, *objects)

    override fun getQuantityString(textId: Int, count: Int, vararg objects: Any) =
        context.resources.getQuantityString(textId, count, *objects)
}
