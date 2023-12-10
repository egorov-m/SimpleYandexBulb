package com.example.simpleyandexbulb.presenter.bulb_control

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleyandexbulb.UiState
import com.example.simpleyandexbulb.data.module.BrightnessLevel
import com.example.simpleyandexbulb.data.module.Color
import com.example.simpleyandexbulb.domain.GetBrightnessLevelsUseCase
import com.example.simpleyandexbulb.domain.GetColorNamesUseCase
import com.example.simpleyandexbulb.domain.GetCurrentColorUseCase
import com.example.simpleyandexbulb.domain.GetCurrentLevelUseCase
import com.example.simpleyandexbulb.domain.GetStateUseCase
import com.example.simpleyandexbulb.domain.SetBrightnessLevelUseCase
import com.example.simpleyandexbulb.domain.SetColorUseCase
import com.example.simpleyandexbulb.domain.SetStateOffUseCase
import com.example.simpleyandexbulb.domain.SetStateOnUseCase
import com.example.simpleyandexbulb.toUiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class YandexBulbViewModel @Inject constructor(
    private val getBrightnessLevelsUseCase: GetBrightnessLevelsUseCase,
    private val getColorNamesUseCase: GetColorNamesUseCase,
    private val getCurrentColorUseCase: GetCurrentColorUseCase,
    private val getCurrentLevelUseCase: GetCurrentLevelUseCase,
    private val getStateUseCase: GetStateUseCase,
    private val setBrightnessLevelUseCase: SetBrightnessLevelUseCase,
    private val setColorUseCase: SetColorUseCase,
    private val setStateOffUseCase: SetStateOffUseCase,
    private val setStateOnUseCase: SetStateOnUseCase
): ViewModel() {

    private val _liveBrightnessLevelData = MutableLiveData<UiState<BrightnessLevel?>>(UiState.Loading)
    val liveBrightnessLevelData: LiveData<UiState<BrightnessLevel?>>
        get() = _liveBrightnessLevelData

    private val _liveColorNamesData = MutableLiveData<UiState<List<String>?>>(UiState.Loading)
    val liveColorNamesData: LiveData<UiState<List<String>?>>
        get() = _liveColorNamesData

    private val _liveCurrentColorData = MutableLiveData<UiState<Color?>>(UiState.Loading)
    val liveCurrentColorData: LiveData<UiState<Color?>>
        get() = _liveCurrentColorData

    private val _liveCurrentLevelData = MutableLiveData<UiState<Int?>>(UiState.Loading)
    val liveCurrentLevelData: LiveData<UiState<Int?>>
        get() = _liveCurrentLevelData

    private val _liveStateData = MutableLiveData<UiState<Boolean?>>(UiState.Loading)
    val liveStateData: LiveData<UiState<Boolean?>>
        get() = _liveStateData

    fun loadBrightnessLevelData() {
        viewModelScope.launch {
            val brightnessResult = getBrightnessLevelsUseCase()
            _liveBrightnessLevelData.postValue(brightnessResult.toUiState())
        }
    }

    fun loadColorNamesData() {
        viewModelScope.launch {
            val colorNamesResult = getColorNamesUseCase()
            _liveColorNamesData.postValue(colorNamesResult.toUiState())
        }
    }

    fun loadCurrentColorData() {
        viewModelScope.launch {
            val currentColor = getCurrentColorUseCase()
            _liveCurrentColorData.postValue(currentColor.toUiState())
        }
    }

    fun loadCurrentBrightnessLevelData() {
        viewModelScope.launch {
            val currentLevel = getCurrentLevelUseCase()
            _liveCurrentLevelData.postValue(currentLevel.toUiState())
        }
    }

    fun setBrightnessLevel(level: Int) {
        viewModelScope.launch {
            val isSet = setBrightnessLevelUseCase(level)
            if (isSet.getOrNull() == true) {
                _liveCurrentLevelData.postValue(UiState.Success(level))
            } else {
                _liveCurrentLevelData.postValue(UiState.Failure("Error set level"))
            }
        }
    }

    fun setColor(color: String) {
        viewModelScope.launch {
            val isSet = setColorUseCase(color)
            if (isSet.getOrNull() == true) {
                _liveCurrentColorData.postValue(UiState.Success(Color(0, color, color, color)))
            } else {
                _liveCurrentColorData.postValue(UiState.Failure("Error set color"))
            }
        }
    }

    fun setState(state: Boolean) {
        viewModelScope.launch {
            val isSet = if (state) setStateOnUseCase() else setStateOffUseCase()
            if (isSet.getOrNull() == true) {
                _liveStateData.postValue(UiState.Success(state))
            } else {
                _liveStateData.postValue(UiState.Failure("Error set state"))
            }

            val colorNamesResult = getColorNamesUseCase()
            _liveColorNamesData.postValue(colorNamesResult.toUiState())
            val currentColor = getCurrentColorUseCase()
            _liveCurrentColorData.postValue(currentColor.toUiState())
            val currentLevel = getCurrentLevelUseCase()
            _liveCurrentLevelData.postValue(currentLevel.toUiState())
        }
    }

    fun loadStateData() {
        viewModelScope.launch {
            val stateResult = getStateUseCase()
            _liveStateData.postValue(stateResult.toUiState())
        }
    }
}
