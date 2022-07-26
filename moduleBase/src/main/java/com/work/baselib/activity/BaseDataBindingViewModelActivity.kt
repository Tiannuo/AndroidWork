package com.work.baselib.activity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

/**
 * @Author TIKOU
 * @Date 2022/7/26-14:40
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
abstract class BaseDataBindingViewModelActivity<B : ViewDataBinding, VM : ViewModel> :
    BaseActivity() {
    private lateinit var binding: B
    private lateinit var vm: VM
    override fun initBaseMvpModel() {
        super.initBaseMvpModel()
        binding = DataBindingUtil.setContentView(this, getLayout())
        binding.lifecycleOwner = this
        getGenericVM()
        bindingVM(binding,vm)
    }

    private fun getGenericVM() {
        val parameterizedType = javaClass.genericSuperclass as? ParameterizedType
        val vmClass = parameterizedType?.actualTypeArguments?.getOrNull(1) as? Class<VM>?
        if (vmClass != null) {
            vm = ViewModelProvider(this)[vmClass]
        }
    }
    protected fun getBinding() = binding
    protected fun getVm() = vm

    /**
     * 确定布局
     * @return Int
     */
    abstract fun getLayout(): Int

    /**
     * 绑定viewModel，由于viewModel是在我们新建布局的时候定义的name，所以目前无法自动绑定，需要手动绑定
     *        binding.vm = vm   （   binding.vm的vm是binding中的name key）
     */
    abstract fun bindingVM(binding: B, vm: VM)
}