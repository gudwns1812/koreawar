package dev.hjp.koreawargame.presentation.viewmodel.game

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import dev.hjp.koreawargame.data.repository.BattleRepository
import dev.hjp.koreawargame.data.repository.TaxRepository
import dev.hjp.koreawargame.domain.domaindata.war.BattleCity
import dev.hjp.koreawargame.domain.domaindata.war.Country

class BattleViewModel(
    private val battleRepository: BattleRepository,
    private val taxRepository: TaxRepository
) : ViewModel() {

    val battleCountries = battleRepository.countries.value

    val currentTarget = battleRepository.currentTarget.value

    private val _selectedCity = mutableStateOf<BattleCity?>(null)
    val selectedCity: State<BattleCity?> = _selectedCity

    private val _selectedColor = mutableStateOf<Color?>(null)
    val selectedColor: State<Color?> = _selectedColor

    fun findCountry(countryName: String): Country {
        return battleCountries.first { it.countryName == countryName }
    }

    fun clear(battleCity: BattleCity) {
        battleRepository.clearCity()
        taxRepository.increaseRegionCount()
    }

    fun selectCity(
        battleCity: BattleCity,
        color: Color
    ) {
        _selectedCity.value = battleCity
        _selectedColor.value = color
    }
}
