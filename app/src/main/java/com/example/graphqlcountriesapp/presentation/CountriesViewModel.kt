package com.example.graphqlcountriesapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphqlcountriesapp.domain.DetailedCountry
import com.example.graphqlcountriesapp.domain.GetCountriesUseCase
import com.example.graphqlcountriesapp.domain.GetCountryUseCase
import com.example.graphqlcountriesapp.domain.SimpleCountry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCountryUseCase: GetCountryUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(CountriesState())
    val state: State<CountriesState> = _state

    init {
        viewModelScope.launch {
            _state.value = CountriesState(isLoading = true)
            _state.value = _state.value.copy(countries = getCountriesUseCase(), isLoading = false)
        }
    }

    fun selectCountry(code: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(selectedCountry = getCountryUseCase(code))
        }
    }

    fun dismissCountryDialog() {
        _state.value = _state.value.copy(selectedCountry = null)
    }

    data class CountriesState(
        val countries: List<SimpleCountry> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCountry: DetailedCountry? = null,
    )
}
