package dev.hjp.koreawargame.presentation.viewmodel.game

import NorthKoreaTaxProvince
import SouthKoreaTaxProvince
import SouthKoreaTaxProvince.JEJU
import TaxProvince
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hjp.koreawargame.data.repository.game.GameRepository
import dev.hjp.koreawargame.domain.ArmyState
import dev.hjp.koreawargame.domain.EconomyState
import dev.hjp.koreawargame.domain.GoldState
import dev.hjp.koreawargame.domain.ProvinceState
import dev.hjp.koreawargame.domain.ResearchState
import dev.hjp.koreawargame.domain.domaindata.Facilities
import dev.hjp.koreawargame.domain.domaindata.ResearchItem
import dev.hjp.koreawargame.domain.domaindata.UnitType
import dev.hjp.koreawargame.domain.domaindata.war.Country
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class GameViewModel(
    gameRepository: GameRepository
) : ViewModel() {

    private val _gold = MutableStateFlow(GoldState())
    val gold = _gold

    private val _economy = MutableStateFlow(EconomyState())
    val economy = _economy

    private val _research = MutableStateFlow(ResearchState())

    val research = _research
    private val _army = MutableStateFlow(ArmyState())

    val army = _army

    private val _southProvince = MutableStateFlow(
        SouthKoreaTaxProvince.entries.map { province ->
            if (province == JEJU) ProvinceState(province, true)
            else ProvinceState(province)
        }
    )
    val southProvince = _southProvince

    private val _northProvince = MutableStateFlow(
        NorthKoreaTaxProvince.entries.map { province ->
            ProvinceState(province)
        }
    )
    val northProvince = _northProvince

    private val _gameOverEvent = MutableSharedFlow<Boolean>()
    val gameOverEvent = _gameOverEvent.asSharedFlow()

    fun buyUnit(
        unitType: UnitType,
        times: Int = 1
    ) {
        if (_research.value.researchPoints < unitType.researchLimit || _gold.value.gold < unitType.cost * times) {
            return
        }

        _gold.value = _gold.value.copy(
            gold = _gold.value.gold - unitType.cost
        )

        _army.value = _army.value.copy(
            militaryPower = _army.value.militaryPower + unitType.attackPower,
            fightPower = _army.value.fightPower + unitType.attackPower * 0.1
        )

        if (unitType == UnitType.NUCLEAR_MISSILE) {
            _economy.value = _economy.value.copy(
                approvalRate = _economy.value.approvalRate - 2
            )
            checkGameOver()
        }
    }

    fun buyUnit10Times(unitType: UnitType) {
        repeat(10) {
            buyUnit(
                unitType = unitType,
                times = 10
            )
        }
    }

    fun buyFacilities(facilities: Facilities) {
        if (_gold.value.gold < facilities.cost || _economy.value.regionCount < facilities.limitRegionCount) {
            return
        }

        _gold.value = _gold.value.copy(
            gold = _gold.value.gold - facilities.cost
        )

        _economy.value = _economy.value.copy(
            population = _economy.value.population + facilities.populationIncrease,
            economyPower = _economy.value.economyPower + facilities.economyPowerIncrease,
            approvalRate = _economy.value.approvalRate + facilities.approvalRateIncrease
        )
    }

    private fun checkGameOver() {
        if (_economy.value.approvalRate <= 0) {
            viewModelScope.launch {
                _gameOverEvent.emit(true)
            }
        }
    }

    fun buyResearch(research: ResearchItem) {
        if (_gold.value.gold < research.cost || _economy.value.economyPower < research.limitEconomyPower) {
            return
        }

        _gold.value = _gold.value.copy(
            gold = _gold.value.gold - research.cost
        )

        _research.value = _research.value.copy(
            researchPoints = _research.value.researchPoints + research.researchPointIncrease
        )
    }

    fun onProvinceClick(province: TaxProvince) {

        val population = _economy.value.population

        _gold.value = _gold.value.copy(
            gold = _gold.value.gold + population * province.taxRate / 100
        )
    }

    fun isUnLock(province: TaxProvince): Boolean {
        return when (province) {
            is SouthKoreaTaxProvince ->
                southProvince.value.find { it.province == province }!!.hasRegion

            is NorthKoreaTaxProvince ->
                northProvince.value.find { it.province == province }!!.hasRegion
        }
    }

    fun battle() {
        _economy.value = _economy.value.copy(
            approvalRate = _economy.value.approvalRate - 0.5,
            population = _economy.value.population - 100
        )
        checkGameOver()
    }

    fun damagePlayer(enemyMilitaryPower: Long) {
        _army.value = _army.value.copy(
            militaryPower = (_army.value.militaryPower - (enemyMilitaryPower / 10)).coerceAtLeast(0)
        )
    }

    fun winSettle(
        country: Country
    ) {
        _economy.value = _economy.value.copy(
            population = _economy.value.population + country.clearIncreasePopulation,
            approvalRate = _economy.value.approvalRate - country.clearDecreaseApprovalRate
        )
        increaseRegionCount()
    }

    fun reset() {
        _gold.value = GoldState()
        _economy.value = EconomyState()
        _research.value = ResearchState()
        _army.value = ArmyState()

        _southProvince.value = SouthKoreaTaxProvince.entries.map { province ->
            if (province == JEJU) ProvinceState(province, hasRegion = true)
            else ProvinceState(province)
        }

        _northProvince.value = NorthKoreaTaxProvince.entries.map { province ->
            ProvinceState(province)
        }

        viewModelScope.launch {
            _gameOverEvent.emit(false)
        }
    }

    fun increaseRegionCount() {
        _economy.value = _economy.value.copy(
            regionCount = _economy.value.regionCount + 1
        )

        val count = _economy.value.regionCount

        _southProvince.value = _southProvince.value.map {
            if (count >= it.province.unlockAt) it.copy(hasRegion = true) else it
        }

        _northProvince.value = _northProvince.value.map {
            if (count >= it.province.unlockAt) it.copy(hasRegion = true) else it
        }
    }
}
