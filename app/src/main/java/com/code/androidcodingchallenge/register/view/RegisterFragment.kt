package com.code.androidcodingchallenge.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.code.androidcodingchallenge.R
import com.code.androidcodingchallenge.databinding.FragmentRegisterBinding
import com.code.androidcodingchallenge.register.viewmodel.RegisterViewModel
import com.code.androidcodingchallenge.userpreferences.UserPreferences
import com.code.androidcodingchallenge.utils.hide
import com.code.androidcodingchallenge.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var userPreferences: UserPreferences

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        userPreferences = UserPreferences(requireContext())
        binding.buttonRegister.setOnClickListener {
            registration()
        }

        binding.textViewLoginNow.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return binding.root
    }

    private fun registration() {
        val fullName = binding.editTextName.text.toString().trim()
        val userName = binding.editTextTextUserName.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()

        when {
            fullName.isEmpty() -> {
                binding.editTextName.error = "Please enter a valid full name"
                binding.editTextName.requestFocus()
                return
            }
            userName.isEmpty() -> {
                binding.editTextTextUserName.error = "Please enter a valid user name"
                binding.editTextTextUserName.requestFocus()
                return
            }
            password.isEmpty() -> {
                binding.editTextTextPassword.error = "Please enter a valid password"
                binding.editTextTextPassword.requestFocus()
                return
            }
            else -> {
                binding.progressBar.show()

                lifecycleScope.launch {
                    val registeredUserName: String = userPreferences.userUserNameFlow.first()
                    if (registeredUserName == userName) {
                        Toast.makeText(context, "User already registered with provided user name...", Toast.LENGTH_SHORT).show()
                        binding.progressBar.hide()
                    } else {
                        viewModel.saveData(fullName, userName, password)
                        Toast.makeText(context, "User registered successfully...", Toast.LENGTH_LONG).show()
                        Navigation.findNavController(requireView())
                                .navigate(R.id.action_registerFragment_to_loginFragment)
                        binding.progressBar.hide()
                        /*try {
                            val authResponse = viewModel.registration(fullName, userName, password)

                            Toast.makeText(context, authResponse.message, Toast.LENGTH_LONG).show()

                            viewModel.saveData(fullName, userName, password)
                            Navigation.findNavController(requireView())
                                    .navigate(R.id.action_registerFragment_to_loginFragment)
                            binding.progressBar.hide()

                        } catch (e: ApiException) {
                            e.message?.let { binding.root.snackbar(it) }
                            binding.progressBar.hide()
                            e.printStackTrace()
                        } catch (e: NoInternetException) {
                            e.message?.let { binding.root.snackbar(it) }
                            binding.progressBar.hide()
                            e.printStackTrace()
                        }*/
                    }

                }


            }
        }

    }

}