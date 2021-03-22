package com.code.androidcodingchallenge.homescreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.code.androidcodingchallenge.AuthActivity
import com.code.androidcodingchallenge.databinding.FragmentHomeBinding
import com.code.androidcodingchallenge.userpreferences.UserPreferences
import com.code.androidcodingchallenge.utils.startNewActivity
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var userPreferences: UserPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        userPreferences = UserPreferences(requireContext())
        observeData()
        binding.buttonLogout.setOnClickListener {
            logout()
        }

        return binding.root
    }

    private fun logout() = lifecycleScope.launch{
        userPreferences.clear()
        requireActivity().startNewActivity(AuthActivity::class.java)
    }

    @SuppressLint("SetTextI18n")
    private fun observeData(){
        userPreferences.userFullNameFlow.asLiveData().observe(requireActivity(),{
            binding.textViewName.text = "Welcome $it"
        })
    }

}