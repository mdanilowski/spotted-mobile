package pl.mdanilowski.spotted.ui.details

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import pl.mdanilowski.spotted.R
import pl.mdanilowski.spotted.base.BaseActivity
import pl.mdanilowski.spotted.data.model.Message
import pl.mdanilowski.spotted.databinding.ActivityMessageDetailsBinding

class MessageDetailsActivity :
    BaseActivity<ActivityMessageDetailsBinding, MessageDetailsViewModel>(MessageDetailsViewModel::class) {

    companion object {
        const val MESSAGE_TAG = "message"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup(R.layout.activity_message_details, MessageDetailsViewModel::class.java)
        binding.viewModel = viewModel
//        vm.message = intent.getSerializableExtra(MESSAGE_TAG) as Message
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
    startActivity(
        Intent(context, MessageDetailsActivity::class.java).putExtra(
            MessageDetailsActivity.MESSAGE_TAG,
            msg
        )
    )
}