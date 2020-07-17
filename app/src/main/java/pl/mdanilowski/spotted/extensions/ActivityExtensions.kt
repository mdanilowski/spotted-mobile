package pl.mdanilowski.spotted.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

fun AppCompatActivity.setupToolbarWithoutBackNavigation(toolbar: Toolbar?) {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(false)
    supportActionBar?.setHomeButtonEnabled(false)
    supportActionBar?.setDisplayShowTitleEnabled(false)
}