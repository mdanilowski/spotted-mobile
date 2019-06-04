package pl.mdanilowski.myapplication.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


abstract class BaseActivity<B : ViewDataBinding, VM : ViewModel> : DaggerAppCompatActivity() {

    lateinit var binding: B
    lateinit var viewModel: VM

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    fun setup(layoutRes: Int, clazz: Class<VM>) {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(clazz)
    }
}