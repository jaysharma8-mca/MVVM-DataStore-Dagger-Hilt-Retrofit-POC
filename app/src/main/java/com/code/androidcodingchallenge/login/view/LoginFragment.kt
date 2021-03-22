package com.code.androidcodingchallenge.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.code.androidcodingchallenge.R
import com.code.androidcodingchallenge.databinding.FragmentLoginBinding
import com.code.androidcodingchallenge.homescreen.HomeActivity
import com.code.androidcodingchallenge.userpreferences.UserPreferences
import com.code.androidcodingchallenge.utils.hide
import com.code.androidcodingchallenge.utils.show
import com.code.androidcodingchallenge.utils.startNewActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    //private val viewModel: LoginViewModel by viewModels()
    private lateinit var userPreferences: UserPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        userPreferences = UserPreferences(requireContext())
        checkData()

        binding.buttonLogin.setOnClickListener {
            login()
        }

        binding.textViewRegisterNow.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root
    }

    private fun checkData() {
        userPreferences.userName.asLiveData().observe(requireActivity(), {
            if(it == null) {
                Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_registerFragment)
            }
        })
    }

    private fun login(){
        val userName = binding.editTextUserName.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()

        when {
            userName.isEmpty() -> {
                binding.editTextUserName.error = "Please enter a valid user name"
                binding.editTextUserName.requestFocus()
                return
            }
            password.isEmpty() -> {
                binding.editTextPassword.error = "Please enter a valid password"
                binding.editTextPassword.requestFocus()
                return
            }
            else -> {
                binding.progressBar.show()

                lifecycleScope.launch {
                    val registeredUserName = userPreferences.userUserNameFlow.first()
                    val registeredPassword = userPreferences.userPasswordFlow.first()

                    if(registeredUserName == userName && registeredPassword == password){
                        requireActivity().startNewActivity(HomeActivity::class.java)
                        binding.progressBar.hide()
                        Toast.makeText(context, "User Login successful...", Toast.LENGTH_SHORT).show()
                    }
                /* try{
                        val authResponse =  viewModel.login(userName,password)

                        //viewModel.saveLoggedInUser(authResponse.user)
                        Toast.makeText(context, authResponse.message, Toast.LENGTH_LONG).show()
                        requireActivity().startNewActivity(HomeActivity::class.java)
                        binding.progressBar.hide()

                    }
                    catch (e: ApiException){
                        e.message?.let { binding.root.snackbar(it) }
                        binding.progressBar.hide()
                        e.printStackTrace()
                    }
                    catch (e: NoInternetException){
                        e.message?.let { binding.root.snackbar(it) }
                        binding.progressBar.hide()
                        e.printStackTrace()
                    }*/
                }
            }
        }
    }
}