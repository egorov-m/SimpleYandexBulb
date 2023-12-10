package com.example.simpleyandexbulb.presenter.bulb_control

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.simpleyandexbulb.R
import com.example.simpleyandexbulb.UiState
import com.example.simpleyandexbulb.data.module.BrightnessLevel
import com.example.simpleyandexbulb.data.module.Color
import com.example.simpleyandexbulb.databinding.FragmentBulbControlBinding
import com.example.simpleyandexbulb.di.ViewModelFactory
import com.example.simpleyandexbulb.di.appComponent
import com.google.android.material.slider.Slider
import com.google.android.material.switchmaterial.SwitchMaterial
import javax.inject.Inject

class BulbControlFragment : Fragment(R.layout.fragment_bulb_control) {
    private val binding: FragmentBulbControlBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: YandexBulbViewModel by viewModels() { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()
        initializeHandlers()
    }

    private fun loadData() {
        viewModel.liveStateData.observe(viewLifecycleOwner) {
            onStateDataReceived(binding.bulbSwitch, it)
        }
        viewModel.loadStateData()
        val colorsAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, mutableListOf<String>())
        binding.autoCompleteBulbColor.setAdapter(colorsAdapter)
        viewModel.liveColorNamesData.observe(viewLifecycleOwner) {
            onColorsDataReceived(colorsAdapter, it)
        }
        viewModel.loadColorNamesData()
        viewModel.liveCurrentColorData.observe(viewLifecycleOwner) {
            onCurrentColorDataReceived(binding.autoCompleteBulbColor, it)
        }
        viewModel.loadCurrentColorData()
        viewModel.liveBrightnessLevelData.observe(viewLifecycleOwner) {
            onBrightnessLevelDataReceived(binding.sliderBulbBrightness, it)
        }
        viewModel.loadBrightnessLevelData()
        viewModel.liveCurrentLevelData.observe(viewLifecycleOwner) {
            onCurrentBrightnessLevelDataReceived(binding.sliderBulbBrightness, it)
        }
        viewModel.loadCurrentBrightnessLevelData()
    }

    private fun initializeHandlers() {
        binding.bulbSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setState(isChecked)
        }
        binding.autoCompleteBulbColor.setOnItemClickListener { parent, _, position, _ ->
            viewModel.setColor(parent.getItemAtPosition(position).toString())
        }
        binding.sliderBulbBrightness.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {}

            override fun onStopTrackingTouch(slider: Slider) {
                viewModel.setBrightnessLevel(slider.value.toInt())
            }
        })
    }

    private fun onColorsDataReceived(adapter: ArrayAdapter<String>, colorNames: UiState<List<String>?>?) {
        when(colorNames) {
            is UiState.Success -> {
                binding.textInputLayoutBulbColor.isEnabled = true
                binding.textInputLayoutBulbColor.hint = "Color"
                colorNames.value?.let {
                    adapter.clear()
                    for (item in it) {
                        adapter.insert(item, adapter.count)
                    }
                    adapter.notifyDataSetChanged()
                }
            }
            is UiState.Failure -> {
                binding.textInputLayoutBulbColor.isEnabled = false
                binding.textInputLayoutBulbColor.error = "Loading error"
            }
            is UiState.NotAvailable -> {
                binding.textInputLayoutBulbColor.isEnabled = false
                binding.textInputLayoutBulbColor.hint = colorNames.message
            }
            is UiState.Loading -> {
                binding.textInputLayoutBulbColor.isEnabled = true
                binding.textInputLayoutBulbColor.hint = "Loading..."
            }
            else -> {}
        }
    }

    private fun onCurrentColorDataReceived(autoComplete: AutoCompleteTextView, colorNames: UiState<Color?>?) {
        when(colorNames) {
            is UiState.Success -> {
                binding.textInputLayoutBulbColor.isEnabled = true
                colorNames.value?.let {
                    autoComplete.setText(it.color, false)
                }
            }
            is UiState.NotAvailable -> {
                binding.textInputLayoutBulbColor.isEnabled = false
                binding.textInputLayoutBulbColor.hint = colorNames.message
            }
            else -> {}
        }
    }

    private fun onCurrentBrightnessLevelDataReceived(slider: Slider, level: UiState<Int?>?) {
        when(level) {
            is UiState.Success -> {
                slider.isEnabled = true
                level.value?.let {
                    slider.value = it.toFloat()
                }
            }
            is UiState.NotAvailable -> {
                slider.isEnabled = false
            }
            is UiState.Failure -> {
                slider.isEnabled = false
            }
            else -> {}
        }
    }

    private fun onBrightnessLevelDataReceived(slider: Slider, brightness: UiState<BrightnessLevel?>?) {
        when(brightness) {
            is UiState.Success -> {
                slider.isEnabled = true
                brightness.value?.let {
                    slider.valueFrom = it.min.toFloat()
                    slider.valueTo = it.max.toFloat()
                    slider.stepSize = it.precision.toFloat()
                }
            }
            is UiState.Failure -> {
                slider.isEnabled = false
                slider.trackInactiveTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.error))
            }
            is UiState.Loading -> {
                slider.isEnabled = false
            }
            else -> {}
        }
    }

    private fun onStateDataReceived(switch: SwitchMaterial, state: UiState<Boolean?>?) {
        val bulbOff = ContextCompat.getDrawable(requireContext(), R.drawable.bulb_off)
        val bulbOn = ContextCompat.getDrawable(requireContext(), R.drawable.bulb_on)
        when(state) {
            is UiState.Success -> {
                switch.isEnabled = true
                state.value?.let {
                    switch.isChecked = it
                    if (it) binding.bulb.setImageDrawable(bulbOn)
                    else binding.bulb.setImageDrawable(bulbOff)
                }
            }
            is UiState.Failure -> {
                switch.isEnabled = false
                binding.bulb.setImageDrawable(bulbOff)
            }
            is UiState.Loading -> {
                switch.isEnabled = false
                binding.bulb.setImageDrawable(bulbOff)
            }
            else -> {}
        }
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }
}