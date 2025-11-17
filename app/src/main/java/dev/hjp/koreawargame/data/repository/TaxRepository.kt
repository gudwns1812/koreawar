package dev.hjp.koreawargame.data.repository

import NorthKoreaTaxProvince
import SouthKoreaTaxProvince
import SouthKoreaTaxProvince.JEJU
import dev.hjp.koreawargame.domain.ProvinceState
import dev.hjp.koreawargame.domain.RegionCountState
import kotlinx.coroutines.flow.MutableStateFlow

class TaxRepository {

    private val _regionCountState = MutableStateFlow(RegionCountState())
    val regionCountState = _regionCountState

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

    fun increaseRegionCount() {
        _regionCountState.value = _regionCountState.value.copy(
            regionCount = _regionCountState.value.regionCount + 1
        )

        val count = _regionCountState.value.regionCount

        _southProvince.value = _southProvince.value.map {
            if (count >= it.province.unlockAt) it.copy(hasRegion = true) else it
        }

        _northProvince.value = _northProvince.value.map {
            if (count >= it.province.unlockAt) it.copy(hasRegion = true) else it
        }
    }
}