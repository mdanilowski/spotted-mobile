package pl.mdanilowski.spotted.ui.add

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import pl.mdanilowski.spotted.R
import pl.mdanilowski.spotted.base.BaseActivity
import pl.mdanilowski.spotted.databinding.ActivityAddMessageBinding

class AddMessageActivity :
    BaseActivity<ActivityAddMessageBinding, AddMessageViewModel>(AddMessageViewModel::class) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup(R.layout.activity_add_message, AddMessageViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.findViewById(R.id.toolbar))
        supportActionBar?.title = "New spotted message"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}

fun Activity.startAddMessageActivity(context: Context) {
    startActivity(Intent(context, AddMessageActivity::class.java))
}