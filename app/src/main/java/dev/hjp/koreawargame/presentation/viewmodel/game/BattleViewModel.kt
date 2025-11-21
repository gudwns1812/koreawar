package dev.hjp.koreawargame.presentation.viewmodel.game

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hjp.koreawargame.data.repository.BattleRepository
import dev.hjp.koreawargame.data.repository.TaxRepository
import dev.hjp.koreawargame.domain.domaindata.war.Country
import dev.hjp.koreawargame.domain.domaindata.war.jeollaCountry
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class BattleViewModel(
    private val battleRepository: BattleRepository,
    private val taxRepository: TaxRepository
) : ViewModel() {
    val currentTarget = battleRepository.currentTarget
    private val _selectedCountry = mutableStateOf<Country?>(jeollaCountry)
    val selectedCountry: State<Country?> = _selectedCountry

    private val _uiEvent = MutableSharedFlow<UiEvent>(1)
    val uiEvent = _uiEvent.asSharedFlow()

    fun findCountry(countryName: String): Country {
        battleRepository.countries.value.forEach {
            if (it.countryName == countryName) {
                return it
            }
        }
        return jeollaCountry
    }

    fun clear() {
        battleRepository.clearCity()
        battleRepository.changeCurrentTarget()
        taxRepository.increaseRegionCount()
    }

    fun selectCountry(
        country: Country
    ) {
        _selectedCountry.value = country
    }

    fun damageEnemy(
        damage: Long
    ) {
        battleRepository.damageEnemy((damage / 10))
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

    fun enemyHp(): Long {
        return battleRepository.currentTarget.value.militaryPower
    }

    fun startBattle() {
        viewModelScope.launch { _uiEvent.emit(UiEvent.None) }
    }

    sealed class UiEvent {
        object Win : UiEvent()
        object Lose : UiEvent()
        object None : UiEvent()
    }
}
