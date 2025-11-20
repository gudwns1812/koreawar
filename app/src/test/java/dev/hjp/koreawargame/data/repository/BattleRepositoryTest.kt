package dev.hjp.koreawargame.data.repository

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class BattleRepositoryTest {

    private lateinit var repository: BattleRepository

    @Before
    fun setUp() {
        repository = BattleRepository()
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