package pl.mdanilowski.spotted.base

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseActivity<B : ViewDataBinding, VM : ViewModel>(vmClass: KClass<VM>) :
    AppCompatActivity() {

    lateinit var binding: B
    val viewModel: VM by viewModel(vmClass)

    fun setup(layoutRes: Int, clazz: Class<VM>) {
        binding = DataBindingUtil.setContentView(this, layoutRes)
    }
}