package com.spiraldev.cryptoticker.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.spiraldev.cryptoticker.R
import com.spiraldev.cryptoticker.core.common.BaseFragment
import com.spiraldev.cryptoticker.databinding.FragmentSettingsBinding
import com.spiraldev.cryptoticker.ui.MainNavigationFragment
import com.spiraldev.cryptoticker.util.ThemeHelper
import com.spiraldev.cryptoticker.util.ThemeMode
import com.spiraldev.cryptoticker.util.extensions.doOnChange
import timber.log.Timber


class SettingsFragment : MainNavigationFragment() {

    private val viewModel by viewModels<SettingsViewModel> { viewModelFactory }
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = this@SettingsFragment.viewModel
            }
        observeViewModel()
        return binding.root
    }

    override fun initializeViews() {

    }

    override fun observeViewModel() {
        //Change theme
        viewModel.isDarkMode.doOnChange(this) {
            Timber.d("On Theme changed")
            ThemeHelper.applyTheme(if (it) ThemeMode.Dark else ThemeMode.Light)
        }
    }
}