package dev.hjp.koreawargame.data.repository

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
        jeollaCountry.cities[0]
    )
    val currentTarget: StateFlow<BattleCity> = _currentTarget

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

    fun damageEnemy(damage: Long) {
        _currentTarget.value = _currentTarget.value.copy(
            militaryPower = _currentTarget.value.militaryPower - damage
        )
    }

}
