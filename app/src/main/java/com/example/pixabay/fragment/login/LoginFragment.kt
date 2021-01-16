package com.example.pixabay.fragment.login

import androidx.core.widget.doOnTextChanged
import com.example.pixabay.MainActivity
import com.example.pixabay.R
import com.example.pixabay.base.FragmentBaseMVVM
import com.example.pixabay.base.utils.viewBinding
import com.example.pixabay.databinding.FragmentLoginBinding
import com.example.pixabay.utils.closeKeyboard
import com.example.pixabay.utils.gone
import com.example.pixabay.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : FragmentBaseMVVM<LoginViewModel, FragmentLoginBinding>() {

    override val viewModel: LoginViewModel by viewModel()
    override val binding: FragmentLoginBinding by viewBinding()

    override fun initView() {
        with(binding) {
            login.setOnClickListener {
                loading.visible()
                viewModel.login(
                    username.text.toString(),
                    password.text.toString()
                )
            }
            username.doOnTextChanged { text, start, before, count ->
                emailInputLayout.isErrorEnabled = false
            }
            password.doOnTextChanged { text, start, before, count ->
                passwordInputLayout.isErrorEnabled = false
            }
            registration.setOnClickListener {
                navigateFragment(R.id.action_navigationLogin_to_navigationRegistration)
            }
        }
    }

    override fun observes() {
        with(binding) {
            observe(viewModel.loginResult) {
                loading.gone()
                activity?.closeKeyboard()
                navigateFragment(R.id.action_navigationLogin_to_navigationHome)
            }
            observe(viewModel.loginIsIncorrect) {
                loading.gone()
                emailInputLayout.error = getString(R.string.invalid_email_syntax)
            }
            observe(viewModel.passwordIsIncorrect) {
                loading.gone()
                passwordInputLayout.error = getString(R.string.invalid_password_syntax)
            }
            observe(viewModel.loginResultError) {
                loading.gone()
                emailInputLayout.error = getString(R.string.invalid_username)
                passwordInputLayout.error = getString(R.string.invalid_password)
            }
        }
    }

    override fun navigateUp() {
        (activity as MainActivity).finish()
    }
}