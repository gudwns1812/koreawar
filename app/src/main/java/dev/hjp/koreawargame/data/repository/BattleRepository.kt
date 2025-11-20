package dev.hjp.koreawargame.data.repository

import dev.hjp.koreawargame.domain.BattleProvinceState
import dev.hjp.koreawargame.domain.domaindata.war.BattleCity
import dev.hjp.koreawargame.domain.domaindata.war.Country
import dev.hjp.koreawargame.domain.domaindata.war.initialCountries
import dev.hjp.koreawargame.domain.domaindata.war.jeollaCountry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class BattleRepository {

    private val _countries = MutableStateFlow(initialCountries)
    val countries: StateFlow<List<Country>> = _countries

    private val _currentTarget = MutableStateFlow(
        BattleProvinceState(
            battleCity = jeollaCountry.cities[0]
        )
    )
    val currentTarget: StateFlow<BattleProvinceState> = _currentTarget

    fun clearCity() {
        _countries.update { list ->
            list.map { country ->
                country.copy(
                    cities = country.cities.map { city ->
                        if (city == currentTarget.value.battleCity) {
                            changeCurrentTarget(city)
                            city.copy(
                                isClear = true
                            )
                        } else city
                    }
                )
            }
        }

    }

    private fun changeCurrentTarget(battleCity: BattleCity) {
        val nextId = _currentTarget.value.battleCity.id + 1

        val nextTarget = _countries.value.flatMap { it.cities }
            .firstOrNull { it.id == nextId }

        _currentTarget.value = _currentTarget.value.copy(
            battleCity = nextTarget ?: battleCity
        )
    }

}
