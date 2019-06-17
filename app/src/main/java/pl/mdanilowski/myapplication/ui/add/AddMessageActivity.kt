package pl.mdanilowski.myapplication.ui.add

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_message.*
import pl.mdanilowski.myapplication.R
import pl.mdanilowski.myapplication.base.BaseActivity
import pl.mdanilowski.myapplication.databinding.ActivityAddMessageBinding

class AddMessageActivity : BaseActivity<ActivityAddMessageBinding, AddMessageViewModel>(), AddMessageView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup(R.layout.activity_add_message, AddMessageViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setupClickListeners()
        setupToolbar()
    }

    override fun setupClickListeners() {
        btnSubmit.setOnClickListener { viewModel.addMessage() }
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