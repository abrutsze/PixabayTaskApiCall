package com.example.pixabay.fragment.registration

import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.pixabay.R
import com.example.pixabay.base.FragmentBaseMVVM
import com.example.pixabay.base.utils.viewBinding
import com.example.pixabay.databinding.FragmentRegistrationBinding
import com.example.pixabay.utils.closeKeyboard
import com.example.pixabay.utils.gone
import com.example.pixabay.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment :
    FragmentBaseMVVM<RegistrationViewModel, FragmentRegistrationBinding>() {
    override val viewModel: RegistrationViewModel by viewModel()
    override val binding: FragmentRegistrationBinding by viewBinding()

    override fun initView() {
        with(binding) {
            registration.setOnClickListener {
                loading.visible()
                viewModel.registration(
                    username.text.toString(),
                    password.text.toString(),
                    age.text.toString()
                )
            }
            username.doOnTextChanged { text, start, before, count ->
                emailInputLayout.isErrorEnabled = false
            }
            password.doOnTextChanged { text, start, before, count ->
                passwordInputLayout.isErrorEnabled = false
            }
        }
    }

    override fun observes() {
        with(binding) {
            observe(viewModel.registrationResult) {
                loading.gone()
                activity?.closeKeyboard()
                navigateFragment(R.id.action_navigationRegistration_to_navigationHome)
            }
            observe(viewModel.loginIsIncorrect) {
                loading.gone()
                emailInputLayout.error = getString(R.string.invalid_email_syntax)
            }
            observe(viewModel.passwordIsIncorrect) {
                loading.gone()
                passwordInputLayout.error = getString(R.string.invalid_password_syntax)
            }
            observe(viewModel.ageIsIncorrect) {
                loading.gone()
                ageInputLayout.error = getString(R.string.invalid_age_syntax)
            }
            observe(viewModel.registrationResultError) {
                loading.gone()
                Toast.makeText(context, "Con't registration", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun navigateUp() {
        navigateBackStack()
    }
}