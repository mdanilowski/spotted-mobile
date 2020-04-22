package pl.mdanilowski.myapplication.ui.details

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import pl.mdanilowski.myapplication.R
import pl.mdanilowski.myapplication.base.BaseActivity
import pl.mdanilowski.myapplication.data.model.Message
import pl.mdanilowski.myapplication.databinding.ActivityMessageDetailsBinding

class MessageDetailsActivity : BaseActivity<ActivityMessageDetailsBinding, MessageDetailsViewModel>(),
    MessageDetailsView {

    companion object {
        const val MESSAGE_TAG = "message"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup(R.layout.activity_message_details, MessageDetailsViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.message = intent.getSerializableExtra(MESSAGE_TAG) as Message
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.findViewById(R.id.toolbar))
        supportActionBar?.title = "Spotted message"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}

fun Activity.startMessageDetailsActivity(context: Context, msg: Message) {
    startActivity(Intent(context, MessageDetailsActivity::class.java).putExtra(MessageDetailsActivity.MESSAGE_TAG, msg))
}