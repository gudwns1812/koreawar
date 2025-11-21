package dev.hjp.koreawargame.presentation.viewmodel.game

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hjp.koreawargame.data.repository.battle.BattleRepository
import dev.hjp.koreawargame.domain.domaindata.war.BattleCity
import dev.hjp.koreawargame.domain.domaindata.war.Country
import dev.hjp.koreawargame.domain.domaindata.war.initialCountries
import dev.hjp.koreawargame.domain.domaindata.war.jeollaCountry
import dev.hjp.koreawargame.domain.gamesave.BattleSaveData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BattleViewModel(
    private val battleRepository: BattleRepository
) : ViewModel() {
    private val _countries = MutableStateFlow(initialCountries)
    val countries: StateFlow<List<Country>> = _countries

    private val _currentTarget = MutableStateFlow(
        jeollaCountry.cities[0]
    )
    val currentTarget: StateFlow<BattleCity> = _currentTarget

    private val _selectedCountry = mutableStateOf<Country?>(jeollaCountry)
    val selectedCountry: State<Country?> = _selectedCountry
    private val _uiEvent = MutableSharedFlow<UiEvent>(1)
    val uiEvent = _uiEvent.asSharedFlow()

    fun findCountry(countryName: String): Country {
        countries.value.forEach {
            if (it.countryName == countryName) {
                return it
            }
        }
        return jeollaCountry
    }

    fun selectCountry(
        country: Country
    ) {
        _selectedCountry.value = country
    }

    fun damageEnemy(damage: Long) {
        _currentTarget.value = _currentTarget.value.copy(
            militaryPower = _currentTarget.value.militaryPower - (damage / 10)
        )
    }

    fun enemyHp(): Long {
        return _currentTarget.value.militaryPower
    }

    fun startBattle() {
        viewModelScope.launch { _uiEvent.emit(UiEvent.None) }
    }

    fun proceedTurn(
        userMilitary: Long,
    ) {
        // HP 체크 후 이벤트 발송
        if (userMilitary <= 0) {
            viewModelScope.launch { _uiEvent.emit(UiEvent.Lose) }
        }
        if (currentTarget.value.militaryPower <= 0) {
            viewModelScope.launch { _uiEvent.emit(UiEvent.Win) }
        }
    }

    sealed class UiEvent {
        object Win : UiEvent()
        object Lose : UiEvent()
        object None : UiEvent()
    }

    fun clear() {
        clearCity()
        changeCurrentTarget()
    }

    fun clearCity() {
        _countries.update { list ->
            list.map { country ->
                country.copy(
                    cities = country.cities.map { city ->
                        if (city.id == currentTarget.value.id) {
                            city.copy(
                                isClear = true
                            )
                        } else city
                    }
                )
            }
        }

        println(_countries.value[0].cities[0])
    }

    fun changeCurrentTarget() {
        val nextId = _currentTarget.value.id + 1

        val nextTarget = _countries.value.flatMap { it.cities }
            .firstOrNull { it.id == nextId }

        _currentTarget.value = nextTarget!!
    }

    fun reset() {
        _countries.value = initialCountries
        _currentTarget.value = jeollaCountry.cities[0]
    }

    fun saveBattleState() {
        viewModelScope.launch {
            val saveData = BattleSaveData(
                countries = _countries.value,
                currentTargetId = _currentTarget.value.id
            )
            battleRepository.save(saveData)
        }
    }

    fun loadBattleState(saveData: BattleSaveData) {
        _countries.value = saveData.countries
        _currentTarget.value = _countries.value
            .flatMap { it.cities }
            .first { it.id == saveData.currentTargetId }
    }
}
