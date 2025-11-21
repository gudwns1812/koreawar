package dev.hjp.koreawargame.presentation.viewmodel.game

import dev.hjp.koreawargame.data.repository.TaxRepository
import dev.hjp.koreawargame.data.repository.battle.FakeBattleRepository
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class BattleViewModelTest {

    private lateinit var repository: BattleViewModel

    @Before
    fun setUp() {
        repository = BattleViewModel(FakeBattleRepository(), TaxRepository())
    }

    @Test
    fun `clearCity updates the city correctly`() = runTest {
        val haenam = repository.countries.value.flatMap { it.cities }
            .first { it.id == 3 }

        assertFalse(haenam.isClear)

        repository.clearCity()

        val haenamCleared = repository.countries.value.flatMap { it.cities }
            .first { it.id == 3 }

        assertTrue(haenamCleared.isClear)
    }
}